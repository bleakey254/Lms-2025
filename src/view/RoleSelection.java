package view;

import strategy.AuditorLoginStrategy;
import strategy.RoleLoginStrategy;
import strategy.TrainerLoginStrategy;

import javax.swing.*;
import java.awt.*;

public class RoleSelection extends JFrame {

    private JButton TrainerLoginButton;
    private JButton AuditorLoginButton;
    private JPanel RoleSelectionPanel;

    public RoleSelection(JFrame parent) {
        super("Select Your Role");

        // Initialize components
        RoleSelectionPanel = new JPanel();
        RoleSelectionPanel.setLayout(new GridLayout(2, 1, 20, 20));
        RoleSelectionPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        TrainerLoginButton = new JButton("Login as Trainer");
        AuditorLoginButton = new JButton("Login as Auditor");

        TrainerLoginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        AuditorLoginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));

        RoleSelectionPanel.add(TrainerLoginButton);
        RoleSelectionPanel.add(AuditorLoginButton);

        add(RoleSelectionPanel);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        // Apply Strategy Pattern here
        setUpButtonListeners();
    }

    private void setUpButtonListeners() {
        TrainerLoginButton.addActionListener(e -> {
            RoleLoginStrategy strategy = new TrainerLoginStrategy();
            strategy.login(this);
        });

        AuditorLoginButton.addActionListener(e -> {
            RoleLoginStrategy strategy = new AuditorLoginStrategy();
            strategy.login(this);
        });
    }
}
