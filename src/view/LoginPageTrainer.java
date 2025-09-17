package view;

import facade.LoginFacade;
import strategy.LoginStrategy;
import model.User;
import session.SessionManager;

import decorator.PanelComponent;
import decorator.BasicPanel;
import decorator.BorderDecorator;
import decorator.ShadowDecorator;

import javax.swing.*;
import java.awt.*;

public class LoginPageTrainer extends JDialog {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;

    public LoginPageTrainer(JFrame parent, LoginStrategy strategy) {
        super(parent, "Trainer Login", true);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Trainer Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Original form panel
        JPanel rawFormPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        rawFormPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        emailField = new JTextField();
        passwordField = new JPasswordField();
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        rawFormPanel.add(new JLabel("Email:"));
        rawFormPanel.add(emailField);
        rawFormPanel.add(new JLabel("Password:"));
        rawFormPanel.add(passwordField);

        loginButton = new JButton("Login");
        rawFormPanel.add(new JLabel()); // spacer
        rawFormPanel.add(loginButton);

        // Decorate the form panel
        PanelComponent base = new BasicPanel(rawFormPanel);
        PanelComponent decoratedFormPanel = new ShadowDecorator(new BorderDecorator(base));

        // Add decorated panel
        add(decoratedFormPanel.getPanel(), BorderLayout.CENTER);
        add(errorLabel, BorderLayout.SOUTH);

        // Login logic using facade
        LoginFacade loginFacade = new LoginFacade(strategy);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            try {
                User trainer = loginFacade.login(email, password);
                SessionManager.getInstance().setCurrentUser(trainer);

                dispose(); // Close the login dialog
                SwingUtilities.invokeLater(() -> new TrainerDashboard().setVisible(true));
            } catch (Exception ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        setSize(400, 250);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
