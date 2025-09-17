package app;

import view.RoleSelection;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Optional: Match native OS look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                // Launch the role selection window
                new RoleSelection(null);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "An error occurred while starting the application:\n" + e.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace(); // In production, consider logging this
            }
        });
    }
}
