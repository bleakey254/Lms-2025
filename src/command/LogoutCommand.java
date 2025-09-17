package command;

import session.SessionManager;

import javax.swing.*;

public class LogoutCommand implements Command {

    private final JFrame currentWindow;

    public LogoutCommand(JFrame currentWindow) {
        this.currentWindow = currentWindow;
    }

    @Override
    public void execute() {
        int choice = JOptionPane.showConfirmDialog(
                currentWindow,
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            SessionManager.getInstance().logout();
            CommandLogger.log("User logged out.");

            // Optional: show login screen again
            JOptionPane.showMessageDialog(null, "You have been logged out.");
            currentWindow.dispose();
        } else {
            CommandLogger.log("Logout cancelled by user.");
        }
    }
}
