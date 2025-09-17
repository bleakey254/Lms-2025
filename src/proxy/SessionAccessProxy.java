package proxy;

import command.SessionReceiver;
import model.Session;
import model.User;
import session.SessionManager;
import strategy.SessionFilterStrategy;
import java.util.List;
import java.util.ArrayList;

public class SessionAccessProxy {
    private final SessionReceiver realReceiver;
    private final User currentUser;

    public SessionAccessProxy() {
        this.realReceiver = new SessionReceiver();
        this.currentUser = SessionManager.getInstance().getCurrentUser();
    }

    public List<Session> getAllSessions() {
        validateAccess();
        return realReceiver.getAllSessions();
    }

    public List<Session> filterSessions(SessionFilterStrategy strategy) {
        validateAccess();
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }
        return realReceiver.filterSessions(strategy);
    }

    private void validateAccess() {
        if (currentUser == null) {
            throw new SecurityException("Access denied: User not logged in");
        }
        if (!currentUser.getUsertype().equalsIgnoreCase("auditor")) {
            throw new SecurityException("Access denied: Only auditors can access sessions");
        }
    }
}
