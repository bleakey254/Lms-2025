package proxy;

import model.Session;
import model.DBConnection;
import strategy.SessionFilterStrategy;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RealSessionReceiver implements SessionReceiver {
    private static final Logger logger = Logger.getLogger(RealSessionReceiver.class.getName());

    @Override
    public List<Session> filter(SessionFilterStrategy strategy) {
        List<Session> allSessions = new ArrayList<>();
        String query = "SELECT * FROM sessions";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Session session = mapResultSetToSession(rs);
                allSessions.add(session);
            }

            // Apply the filter strategy if provided
            return strategy != null ? strategy.filter(allSessions) : allSessions;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching sessions", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Session getSessionById(String sessionId) {
        String query = "SELECT * FROM sessions WHERE session_id = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, Integer.parseInt(sessionId));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToSession(rs);
            }
        } catch (SQLException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Error fetching session with ID: " + sessionId, e);
        }

        return null;
    }

    private Session mapResultSetToSession(ResultSet rs) throws SQLException {
        return new Session(
            rs.getInt("session_id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getInt("course_id"),
            rs.getTimestamp("scheduled_datetime")
        );
    }
}
