package facade;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionManagementFacade {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/lms";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static List<String[]> getAllSessionsForUI() {
        List<String[]> sessions = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            try (PreparedStatement stmt = conn.prepareStatement("SELECT id, datetime, status FROM sessions");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    String id = rs.getString("id");
                    String datetime = rs.getString("datetime");
                    String status = rs.getString("status");
                    String action = status.equalsIgnoreCase("Scheduled") ? "Attend" : "View";
                    sessions.add(new String[]{id, datetime, status, action});
                }
            }
            // Do NOT close the connection here, let the DBConnection singleton manage it if you switch to using it
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sessions;
    }

    public static boolean markAttendance(String sessionId, int userId) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String checkSql = "SELECT * FROM session_attendance WHERE session_id = ? AND user_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, sessionId);
                checkStmt.setInt(2, userId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "You have already marked attendance for this session.");
                    return false;
                }
            }

            String insertSql = "INSERT INTO session_attendance (session_id, user_id) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, sessionId);
                insertStmt.setInt(2, userId);
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Attendance marked successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to mark attendance.");
        }
        return false;
    }

    public static List<model.Session> getAllSessions() {
        List<model.Session> sessions = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            try (PreparedStatement stmt = conn.prepareStatement("SELECT id, course_id, title, datetime, description, status FROM sessions");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int courseId = rs.getInt("course_id");
                    String title = rs.getString("title");
                    java.util.Date scheduledDateTime = rs.getTimestamp("datetime");
                    String description = rs.getString("description");
                    sessions.add(new model.Session(id, title, description, courseId, scheduledDateTime));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }
}
