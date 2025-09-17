package proxy;

import model.User;
import session.SessionManager;
import view.MyEnrollmentsPanelRenderer;

import javax.swing.*;

public class EnrollmentAccessProxy implements EnrollmentReceiver {

    private final MyEnrollmentsPanelRenderer realReceiver;
    private final int auditorId;

    public EnrollmentAccessProxy(int auditorId) {
        this.auditorId = auditorId;
        this.realReceiver = new MyEnrollmentsPanelRenderer(auditorId); // The real business logic
    }

    @Override
    public JPanel getEnrollmentsPanel(int userId) {
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser == null || currentUser.getId() != userId) {
            JOptionPane.showMessageDialog(null,
                    "Access denied. Please log in with a valid user.",
                    "Unauthorized Access", JOptionPane.ERROR_MESSAGE);
            return new JPanel(); // return empty panel on unauthorized access
        }
        return realReceiver.getEnrollmentsPanel(userId);
    }
}
