package repository;

import model.Session;
import strategy.SessionFilterStrategy;
import javax.swing.table.DefaultTableModel;
import java.util.List;

// Interface for session data operations (Repository Pattern)
public interface SessionRepository {
    List<Session> getSessions();
    List<Session> filterSessions(SessionFilterStrategy strategy);
    void markAttendance(String sessionId);
    DefaultTableModel getSessionTableModel(SessionFilterStrategy strategy);
}