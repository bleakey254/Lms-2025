package proxy;

import model.User;
import session.SessionManager;

import javax.swing.JTable;
import java.util.logging.Logger;

public class ResultsAccessProxy implements ResultsReceiver {

    private final RealResultsReceiver realReceiver = new RealResultsReceiver();
    private static final Logger logger = Logger.getLogger(ResultsAccessProxy.class.getName());
    private final SessionManager sessionManager = SessionManager.getInstance();

    @Override
    public JTable fetchResultsTable(int userId) {
        if (!hasPermission(userId)) {
            logger.warning("🚫 Unauthorized access attempt by userId=" + sessionManager.getCurrentUser().getId());
            return new JTable(); // return empty table
        }

        logger.info("✅ Results accessed by userId=" + sessionManager.getCurrentUser().getId());
        return realReceiver.fetchResultsTable(userId);
    }

    private boolean hasPermission(int userId) {
        User currentUser = sessionManager.getCurrentUser();
        return currentUser != null && currentUser.getId() == userId;
    }
}
