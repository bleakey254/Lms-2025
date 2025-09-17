package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());
    private static final String URL = "jdbc:mysql://localhost:3306/lms";
    private static final String USER = "root";
    private static final String PASSWORD = "2004@Leakey";

    private static DBConnection instance;
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("JDBC Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            logger.severe("JDBC Driver not found!");
            throw new RuntimeException("JDBC Driver not found!", e);
        }
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();
        try {
            if (conn == null || conn.isClosed()) {
                Properties props = new Properties();
                props.setProperty("user", USER);
                props.setProperty("password", PASSWORD);
                props.setProperty("useSSL", "false");
                props.setProperty("serverTimezone", "UTC");
                props.setProperty("allowPublicKeyRetrieval", "true");

                conn = DriverManager.getConnection(URL, props);
                threadLocal.set(conn);
                logger.info("Database connection established successfully");
            }
            return conn;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to connect to database. URL: " + URL + ", User: " + USER, e);
            throw e;
        }
    }

    public static void closeConnection() {
        try {
            Connection conn = threadLocal.get();
            if (conn != null && !conn.isClosed()) {
                conn.close();
                logger.info("Database connection closed successfully");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error closing database connection", e);
        } finally {
            threadLocal.remove();
        }
    }
}
