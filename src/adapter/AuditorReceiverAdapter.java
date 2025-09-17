package adapter;

import command.AuditorReceiver;
import command.GenericPanelCommand;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class AuditorReceiverAdapter {
    private final AuditorReceiver receiver;
    private final AuditorActionsAdapter actionsAdapter;

    public AuditorReceiverAdapter(AuditorReceiver receiver, JPanel contentPanel, CardLayout cardLayout) {
        this.receiver = receiver;
        this.actionsAdapter = new AuditorActionsAdapter(contentPanel, cardLayout);
    }

    public void exportCourses() {
        receiver.showCourseMaterials(); // mapped to showCourseMaterials
    }

    public void viewCourses() {
        receiver.showEnrollCourses(); // mapped to showEnrollCourses
    }

    public void viewNotifications() {
        // No direct method, so add a stub or log a message
        // You may want to implement this in AuditorReceiver
        System.out.println("Notifications view not implemented.");
    }

    public GenericPanelCommand createPanelCommand(String panelName) {
        return actionsAdapter.createPanelCommand(panelName);
    }
}
