package dao;

import model.Course;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CourseDAOImpl implements CourseDAO {
    // auditorId may be -1 for general (no-auditor) instance
    private int auditorId;
    private Connection connection; // optional externally provided connection

    // Existing constructor
    public CourseDAOImpl(int auditorId) {
        this.auditorId = auditorId;
    }

    // No-arg constructor for singleton usage
    public CourseDAOImpl() {
        this.auditorId = -1;
    }

    // Simple singleton accessor for code that expects getInstance()
    private static volatile CourseDAOImpl instance;

    public static synchronized CourseDAOImpl getInstance() {
        if (instance == null) {
            instance = new CourseDAOImpl();
        }
        return instance;
    }

    // Allow external code to inject a Connection (used by some callers)
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Map<String, List<Course>> getCoursesGroupedByCategory() {
        Map<String, List<Course>> coursesByCategory = new HashMap<>();
        String query = """
            SELECT c.id, c.trainer_id, c.name, c.level, c.cost, c.description, c.category, c.start_date, c.end_date, c.created_at
            FROM courses c
            WHERE c.id NOT IN (
                SELECT course_id FROM enrollments WHERE auditor_id = ?
            )
            ORDER BY c.category, c.name
        """;

        Connection conn = null;
        try {
            conn = (this.connection != null) ? this.connection : DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, auditorId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int courseId = rs.getInt("id");
                        int trainerId = rs.getInt("trainer_id");
                        String name = rs.getString("name");
                        String level = rs.getString("level");
                        double cost = rs.getDouble("cost");
                        String description = rs.getString("description");
                        String categoryName = rs.getString("category");
                        java.util.Date startDate = rs.getDate("start_date");
                        java.util.Date endDate = rs.getDate("end_date");
                        java.util.Date createdAt = rs.getDate("created_at");
                        Course course = new Course(courseId, trainerId, name, level, cost, description, categoryName, startDate, endDate, createdAt);
                        coursesByCategory.computeIfAbsent(categoryName, k -> new ArrayList<>()).add(course);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to load courses: " + e.getMessage());
        }
        return coursesByCategory;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT id, trainer_id, name, level, cost, description, category, start_date, end_date, created_at FROM courses";
        Connection conn = null;
        try {
            conn = (this.connection != null) ? this.connection : DatabaseConnection.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int courseId = rs.getInt("id");
                    int trainerId = rs.getInt("trainer_id");
                    String name = rs.getString("name");
                    String level = rs.getString("level");
                    double cost = rs.getDouble("cost");
                    String description = rs.getString("description");
                    String categoryName = rs.getString("category");
                    java.util.Date startDate = rs.getDate("start_date");
                    java.util.Date endDate = rs.getDate("end_date");
                    java.util.Date createdAt = rs.getDate("created_at");
                    Course course = new Course(courseId, trainerId, name, level, cost, description, categoryName, startDate, endDate, createdAt);
                    list.add(course);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to load all courses: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Course getCourseById(int id) {
        // Minimal implementation to satisfy interface; can be expanded
        String sql = "SELECT * FROM courses WHERE id = ?";
        Connection conn = null;
        try {
            conn = (this.connection != null) ? this.connection : DatabaseConnection.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int courseId = rs.getInt("id");
                        int trainerId = rs.getInt("trainer_id");
                        String name = rs.getString("name");
                        String level = rs.getString("level");
                        double cost = rs.getDouble("cost");
                        String description = rs.getString("description");
                        String categoryName = rs.getString("category");
                        java.util.Date startDate = rs.getDate("start_date");
                        java.util.Date endDate = rs.getDate("end_date");
                        java.util.Date createdAt = rs.getDate("created_at");
                        return new Course(courseId, trainerId, name, level, cost, description, categoryName, startDate, endDate, createdAt);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to load course: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addCourse(Course course) {
        // Not implemented here
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        // Not implemented here
        return false;
    }

    @Override
    public boolean deleteCourse(int id) {
        // Not implemented here
        return false;
    }
}