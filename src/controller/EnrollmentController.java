package controller;

import model.Enrollment;
import model.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentController {

    public List<Enrollment> getEnrollmentsByUserId(int userId) {
        List<Enrollment> enrollments = new ArrayList<>();

        String query = "SELECT * FROM enrollments WHERE user_id = ?";
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int courseId = rs.getInt("course_id");
                    LocalDate enrolledDate = rs.getDate("enrolled_date").toLocalDate();

                    enrollments.add(new Enrollment(id, userId, courseId, enrolledDate));
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error fetching enrollments: " + e.getMessage());
        }

        return enrollments;
    }
}
