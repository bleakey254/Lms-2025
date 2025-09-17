package command;

import model.Session;
import strategy.SessionFilterStrategy;
import java.util.List;
import java.util.ArrayList;

public class SessionReceiver {
    public void joinSession(Session session) {
        // TODO: Implement session joining logic
        System.out.println("Joining session: " + session);
    }

    public List<Session> getAllSessions() {
        // TODO: Implement fetching all sessions from the database/storage
        return new ArrayList<>();
    }

    public List<Session> filterSessions(SessionFilterStrategy strategy) {
        List<Session> allSessions = getAllSessions();
        return strategy.filter(allSessions);
    }
}
