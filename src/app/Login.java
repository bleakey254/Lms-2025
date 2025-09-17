package app;

import controller.UserController;
import factory.DashboardFactory;
import factory.DashboardFactoryProducer;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Login {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        JFrame frame = new JFrame("LMS Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        GridBagConstraints gbc = new GridBagConstraints();

        // Username label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(new JLabel("Username/Email:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        frame.add(usernameField, gbc);

        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        frame.add(passwordField, gbc);

        // Login button
        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton loginButton = new JButton("Login");
        frame.add(loginButton, gbc);

        // Action listener
        loginButton.addActionListener(e -> {
            try {
                handleLogin(frame);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame,
                        "Database error: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private void handleLogin(JFrame loginFrame) throws SQLException {
        String userInput = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword());

        UserController uc = new UserController();
        User user = uc.authenticate(userInput, pass);

        if (user == null) {
            JOptionPane.showMessageDialog(loginFrame,
                    "Invalid credentials",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        loginFrame.dispose();
        session.SessionManager.getInstance().setCurrentUser(user);
        // Use Factory Method to create appropriate dashboard
        DashboardFactory factory = DashboardFactoryProducer.getFactory(user);
        JFrame dashboard = factory.createDashboard(user);
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}
