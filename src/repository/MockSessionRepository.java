package repository;

import model.Session;
import java.util.ArrayList;
import java.util.List;

public class MockSessionRepository {
    private final List<Session> sessions;

    public MockSessionRepository() {
        this.sessions = new ArrayList<>();
    }

    public List<Session> getAllSessions() {
        return new ArrayList<>(sessions);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public void updateSession(Session session) {
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getId() == session.getId()) {
                sessions.set(i, session);
                break;
            }
        }
    }
}
