package view;

import facade.RegistrationFacade;
import strategy.RegistrationStrategy;
import model.User;

import javax.swing.*;
import java.awt.*;

public class RegisterPageAuditor extends JDialog {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JLabel errorLabel;

    public RegisterPageAuditor(JFrame parent, RegistrationStrategy strategy) {
        super(parent, "Auditor Registration", true);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Auditor Registration", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        emailField = new JTextField();
        passwordField = new JPasswordField();
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        registerButton = new JButton("Register");
        formPanel.add(new JLabel()); // spacer
        formPanel.add(registerButton);

        add(formPanel, BorderLayout.CENTER);
        add(errorLabel, BorderLayout.SOUTH);

        RegistrationFacade registrationFacade = new RegistrationFacade(strategy);

        registerButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            try {
                User auditor = registrationFacade.register(email, password);
                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose(); // Close dialog
                SwingUtilities.invokeLater(() -> new AuditorDashboard().setVisible(true));
            } catch (Exception ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        setSize(400, 250);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
