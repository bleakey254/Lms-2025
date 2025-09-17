package dao;

import model.Course;
import model.Enrollment;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDaoImpl implements EnrollmentDao {
    @Override
    public List<Enrollment> getEnrollmentsByUserId(int userId) {
        List<Enrollment> enrolledCourses = new ArrayList<>();
        String query = """
            SELECT c.course_id, c.trainer_id, c.title, c.level, c.cost, c.description, c.category, c.start_date, c.end_date, c.created_at, e.enrollment_id, e.enrolled_date
            FROM courses c
            JOIN enrollments e ON c.course_id = e.course_id
            WHERE e.auditor_id = ?
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getInt("trainer_id"),
                        rs.getString("title"),
                        rs.getString("level"),
                        rs.getDouble("cost"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getDate("created_at")
                );
                int enrollmentId = rs.getInt("enrollment_id"); // or the correct column name for enrollment id
                java.sql.Date enrolledDateSql = rs.getDate("enrolled_date"); // or the correct column name
                java.time.LocalDate enrolledDate = enrolledDateSql != null ? enrolledDateSql.toLocalDate() : null;
                Enrollment enrollment = new Enrollment(enrollmentId, userId, course.getId(), enrolledDate);
                enrolledCourses.add(enrollment);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching enrollments: " + e.getMessage());
        }
        return enrolledCourses;
    }

    @Override
    public boolean enrollUser(int userId, int courseId) {
        String checkQuery = "SELECT COUNT(*) FROM enrollments WHERE auditor_id = ? AND course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, courseId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("⚠️ Auditor is already enrolled.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking enrollment: " + e.getMessage());
            return false;
        }
        String insertQuery = "INSERT INTO enrollments (auditor_id, course_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
            System.out.println("✅ Enrollment successful!");
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Enrollment failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean unenrollUser(int userId, int courseId) {
        String query = "DELETE FROM enrollments WHERE auditor_id = ? AND course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, courseId);
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                System.out.println("✅ Unenrollment successful!");
                return true;
            } else {
                System.out.println("⚠️ Auditor was not enrolled in the course.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Unenrollment failed: " + e.getMessage());
        }
        return false;
    }
}
