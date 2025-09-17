package controller;

import model.Course;
import model.DBConnection;
import observer.CourseObserver;

import java.sql.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CourseController {
    private static final Logger logger = Logger.getLogger(CourseController.class.getName());
    private long lastUpdateTimestamp = System.currentTimeMillis();
    private Map<String, List<Course>> coursesByCategory = new ConcurrentHashMap<>();
    private final List<CourseObserver> observers = new ArrayList<>();
    private ScheduledExecutorService scheduler;

    public CourseController() {
        startDatabaseObserver();
    }

    public void addObserver(CourseObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        Map<String, List<Course>> coursesCopy = new ConcurrentHashMap<>();
        for (Map.Entry<String, List<Course>> entry : coursesByCategory.entrySet()) {
            coursesCopy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        for (CourseObserver observer : observers) {
            try {
                observer.update(coursesCopy);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error notifying observer: " + e.getMessage(), e);
            }
        }
    }

    public boolean hasDatabaseChanged() {
        long latestTimestamp = getLastUpdatedTimestampFromDB();
        if (latestTimestamp > lastUpdateTimestamp) {
            lastUpdateTimestamp = latestTimestamp;
            return true;
        }
        return false;
    }

    private long getLastUpdatedTimestampFromDB() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT MAX(updated_at) FROM courses")) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Timestamp timestamp = rs.getTimestamp(1);
                    return timestamp != null ? timestamp.getTime() : lastUpdateTimestamp;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching last updated timestamp: " + e.getMessage(), e);
        }
        return lastUpdateTimestamp;
    }

    private void startDatabaseObserver() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (hasDatabaseChanged()) {
                    logger.info("Database changed. Updating UI...");
                    coursesByCategory = fetchCoursesByCategory();
                    notifyObservers();
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, "Database observer error: " + e.getMessage(), e);
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public Map<String, List<Course>> fetchCoursesByCategory() {
        Map<String, List<Course>> theCoursesByCategory = new ConcurrentHashMap<>();
        String query = "SELECT courses.*, cat.categoryName AS category_name " +
                "FROM courses JOIN categories cat ON courses.Category_id = cat.idCategories";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("idCourses"),
                        rs.getInt("trainer_id"),
                        rs.getString("Name"),
                        rs.getString("Level"),
                        rs.getDouble("cost"),
                        rs.getString("Description"),
                        rs.getString("category_name"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getDate("created_at")
                );
                theCoursesByCategory.computeIfAbsent(
                        rs.getString("category_name"), k -> new ArrayList<>()).add(course);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching courses by category: " + e.getMessage(), e);
        }
        coursesByCategory = theCoursesByCategory;
        notifyObservers();
        return coursesByCategory;
    }

    public boolean addCourse(Course course) {
        int categoryId = getCategoryIdByName(course.getCategory());
        if (categoryId == -1) {
            logger.warning("Category not found: " + course.getCategory());
            return false;
        }
        String query = "INSERT INTO courses (trainer_id, Name, Level, cost, Description, Category_id, start_date, end_date, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, course.getTrainerId());
            stmt.setString(2, course.getName());
            stmt.setString(3, course.getLevel());
            stmt.setDouble(4, course.getCost());
            stmt.setString(5, course.getDescription());
            stmt.setInt(6, categoryId);
            stmt.setDate(7, new java.sql.Date(course.getStartDate().getTime()));
            stmt.setDate(8, new java.sql.Date(course.getEndDate().getTime()));
            stmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            boolean success = stmt.executeUpdate() > 0;
            if (success) logger.info("Course added: " + course.getName());
            return success;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding course: " + course.getName(), e);
            return false;
        }
    }

    public int getCategoryIdByName(String categoryName) {
        String query = "SELECT idCategories FROM categories WHERE categoryName = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, categoryName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idCategories");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching category ID for: " + categoryName, e);
        }
        return -1;
    }

    public List<Course> getCoursesByTrainerId(int trainerId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.*, cat.categoryName FROM courses c " +
                "JOIN categories cat ON c.Category_id = cat.idCategories " +
                "WHERE c.trainer_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, trainerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course(
                            rs.getInt("idCourses"),
                            rs.getInt("trainer_id"),
                            rs.getString("Name"),
                            rs.getString("Level"),
                            rs.getDouble("cost"),
                            rs.getString("Description"),
                            rs.getString("categoryName"),
                            rs.getDate("start_date"),
                            rs.getDate("end_date"),
                            rs.getDate("created_at")
                    );
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching courses for trainer ID: " + trainerId, e);
        }
        return courses;
    }

    public boolean updateCourse(Course course) {
        int categoryId = getCategoryIdByName(course.getCategory());
        if (categoryId == -1) {
            logger.warning("Invalid category for course update: " + course.getCategory());
            return false;
        }
        String query = "UPDATE courses SET Name=?, Level=?, cost=?, Description=?, Category_id=?, start_date=?, end_date=?, updated_at=? WHERE idCourses=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course.getName());
            stmt.setString(2, course.getLevel());
            stmt.setDouble(3, course.getCost());
            stmt.setString(4, course.getDescription());
            stmt.setInt(5, categoryId);
            stmt.setDate(6, new java.sql.Date(course.getStartDate().getTime()));
            stmt.setDate(7, new java.sql.Date(course.getEndDate().getTime()));
            stmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(9, course.getId());
            boolean success = stmt.executeUpdate() > 0;
            if (success) logger.info("Course updated: " + course.getName());
            return success;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating course: " + course.getName(), e);
            return false;
        }
    }

    public boolean deleteCourse(int courseId) {
        String query = "DELETE FROM courses WHERE idCourses = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            boolean success = stmt.executeUpdate() > 0;
            if (success) logger.info("Course deleted with ID: " + courseId);
            return success;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting course ID: " + courseId, e);
            return false;
        }
    }

    public Course getCourseById(int courseId) throws SQLException {
        String query = "SELECT c.*, cat.categoryName FROM courses c " +
                "JOIN categories cat ON c.Category_id = cat.idCategories WHERE c.idCourses = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                            rs.getInt("idCourses"),
                            rs.getInt("trainer_id"),
                            rs.getString("Name"),
                            rs.getString("Level"),
                            rs.getDouble("cost"),
                            rs.getString("Description"),
                            rs.getString("categoryName"),
                            rs.getDate("start_date"),
                            rs.getDate("end_date"),
                            rs.getDate("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching course by ID: " + courseId, e);
            throw e;
        }
        return null;
    }
}