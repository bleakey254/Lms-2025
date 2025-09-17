package proxy;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingProxy implements ActionPerformer {
    private static final Logger logger = Logger.getLogger(LoggingProxy.class.getName());
    private final RealActionPerformer performer = new RealActionPerformer();

    @Override
    public void performAction(String action) {
        if (action == null || action.trim().isEmpty()) {
            logger.warning("Attempted to perform an empty or null action.");
            return;
        }

        try {
            logger.info("[" + LocalDateTime.now() + "] Performing action: " + action);
            performer.performAction(action);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to perform action: " + action, e);
        }
    }
}
