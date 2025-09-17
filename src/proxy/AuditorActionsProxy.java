package proxy;

import adapter.AuditorReceiverAdapter;
import command.AuditorReceiver;
import command.GenericPanelCommand;
import model.User;
import session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class AuditorActionsProxy {
    private final AuditorReceiverAdapter realAdapter;
    private static final Logger logger = Logger.getLogger(AuditorActionsProxy.class.getName());
    private final SessionManager sessionManager;
    private final JPanel contentPanel;
    private final CardLayout cardLayout;

    public AuditorActionsProxy(AuditorReceiver receiver, JPanel contentPanel, CardLayout cardLayout) {
        this.contentPanel = contentPanel;
        this.cardLayout = cardLayout;
        this.sessionManager = SessionManager.getInstance();
        this.realAdapter = new AuditorReceiverAdapter(receiver, contentPanel, cardLayout);
    }

    public void viewCourses() {
        if (hasAccess()) {
            realAdapter.viewCourses();
        } else {
            logger.warning("Access denied for viewCourses");
        }
    }

    public void exportCourses() {
        if (hasAccess()) {
            realAdapter.exportCourses();
        } else {
            logger.warning("Access denied for exportCourses");
        }
    }

    public void viewNotifications() {
        if (hasAccess()) {
            realAdapter.viewNotifications();
        } else {
            logger.warning("Access denied for viewNotifications");
        }
    }

    public GenericPanelCommand createPanelCommand(String panelName) {
        if (!hasAccess()) {
            logger.warning("🚫 Unauthorized attempt to create command for panel: " + panelName);
            return null;
        }
        return realAdapter.createPanelCommand(panelName);
    }

    private boolean hasAccess() {
        User currentUser = sessionManager.getCurrentUser();
        return currentUser != null && "Auditor".equalsIgnoreCase(currentUser.getUsertype());
    }
}
