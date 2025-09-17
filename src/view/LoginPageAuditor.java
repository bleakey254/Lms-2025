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

public class LoginPageAuditor extends JDialog {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;

    public LoginPageAuditor(JFrame parent, LoginStrategy strategy) {
        super(parent, "Auditor Login", true);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Auditor Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Original raw panel
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
        rawFormPanel.add(new JLabel()); // Spacer
        rawFormPanel.add(loginButton);

        // Decorated panel
        PanelComponent base = new BasicPanel(rawFormPanel);
        PanelComponent decoratedPanel = new ShadowDecorator(new BorderDecorator(base));

        add(decoratedPanel.getPanel(), BorderLayout.CENTER);
        add(errorLabel, BorderLayout.SOUTH);

        LoginFacade loginFacade = new LoginFacade(strategy);

        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            try {
                User auditor = loginFacade.login(email, password);

                if (auditor == null || !"Auditor".equalsIgnoreCase(auditor.getUsertype())) {
                    errorLabel.setText("Invalid auditor credentials.");
                    return;
                }

                SessionManager.getInstance().setCurrentUser(auditor);
                dispose();
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
