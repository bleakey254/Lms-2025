package controller;

import model.Auditor;
import model.Trainer;
import model.User;
import dao.UserDaoImpl;
import utils.DatabaseConnection;

import java.sql.*;

public class UserController {

    /**
     * Authenticate a user by username/email and password.
     * Returns a fully populated Trainer or Auditor, or null if invalid.
     */
    public User authenticate(String usernameOrEmail, String password) throws SQLException {
        String sql = """
            SELECT idUsers, usertype
              FROM users
             WHERE (username = ? OR email = ?)
               AND password = ?
        """;

        // Obtain the shared connection but do NOT close it here
        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usernameOrEmail);
            ps.setString(2, usernameOrEmail);
            ps.setString(3, password);

            System.out.println("DEBUG: Query params - usernameOrEmail='" + usernameOrEmail + "', password='" + password + "'");
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData meta = rs.getMetaData();
                int columns = meta.getColumnCount();
                System.out.print("DEBUG: ResultSet columns: ");
                for (int i = 1; i <= columns; i++) {
                    System.out.print(meta.getColumnName(i) + " ");
                }
                System.out.println();
                if (!rs.next()) {
                    System.out.println("DEBUG: No user found for credentials: " + usernameOrEmail);
                    return null; // Invalid credentials
                }

                // Print all column values for the matched row
                for (int i = 1; i <= columns; i++) {
                    System.out.println("DEBUG: " + meta.getColumnName(i) + " = " + rs.getString(i));
                }
                // Defensive: print raw value and check for null
                String rawId = rs.getString("idUsers");
                System.out.println("DEBUG: Raw idUsers value from DB: " + rawId);
                int userId = 0;
                if (rawId != null) {
                    try {
                        userId = Integer.parseInt(rawId.trim());
                    } catch (NumberFormatException e) {
                        System.out.println("DEBUG: idUsers value is not a valid integer: " + rawId);
                        return null;
                    }
                } else {
                    System.out.println("DEBUG: idUsers column is NULL!");
                    return null;
                }
                if (userId <= 0) {
                    System.out.println("DEBUG: Invalid idUsers value returned from DB: " + userId);
                    return null;
                }
                String type = rs.getString("usertype");
                System.out.println("DEBUG: Found userId=" + userId + ", usertype=" + type);

                if ("Trainer".equalsIgnoreCase(type) || "Auditor".equalsIgnoreCase(type)) {
                    return new UserDaoImpl().getUserById(userId);
                } else {
                    System.out.println("DEBUG: Unknown usertype: " + type);
                    return null; // Unknown role
                }
            }
        }
    }
}
