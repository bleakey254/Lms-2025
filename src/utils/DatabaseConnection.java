package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/lms?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "2004@Leakey";

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ JDBC Driver not found!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null || conn.isClosed()) {
            System.out.println("Attempting to connect with URL: " + URL + ", User: " + USER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            threadLocal.set(conn);
            System.out.println("🔗 New DB connection created.");
        } else {
            System.out.println("🔗 Reusing existing DB connection.");
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            Connection conn = threadLocal.get();
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("❎ DB connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            threadLocal.remove();
        }
    }
}