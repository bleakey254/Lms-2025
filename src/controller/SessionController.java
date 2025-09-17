package controller;

import model.DBConnection;
import model.Session;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionController {

    /** Insert a new session into the DB */
    public boolean scheduleSession(Session session) {
        String sql = """
            INSERT INTO sessions
              (course_id, title, scheduled_datetime, description)
            VALUES (?, ?, ?, ?)
        """;

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, session.getCourseId());
                ps.setString(2, session.getTitle());
                ps.setTimestamp(3, new Timestamp(session.getScheduledDateTime().getTime()));
                ps.setString(4, session.getDescription());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /** Fetch all sessions for the given trainer's courses */
    public List<Session> getSessionsByTrainerId(int trainerId) {
        List<Session> list = new ArrayList<>();
        String sql = """
            SELECT s.id,
                   s.course_id,
                   s.title,
                   s.scheduled_datetime,
                   s.description
              FROM sessions s
              JOIN courses c ON s.course_id = c.idCourses
             WHERE c.trainer_id = ?
             ORDER BY s.scheduled_datetime
        """;

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, trainerId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Timestamp timestamp = rs.getTimestamp("scheduled_datetime");
                    list.add(new Session(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("course_id"),
                        timestamp // This will be automatically converted to Date
                    ));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
