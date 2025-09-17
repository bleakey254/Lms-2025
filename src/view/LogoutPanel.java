package view;

import command.LogoutCommand;
import decorator.BasicPanel;
import decorator.BorderDecorator;
import decorator.PanelComponent;
import decorator.ShadowDecorator;

import javax.swing.*;
import java.awt.*;

public class LogoutPanel extends JPanel {

    public LogoutPanel(JFrame parentFrame) {
        // Apply ShadowDecorator + BorderDecorator
        PanelComponent decorated = new ShadowDecorator(
                new BorderDecorator(
                        new BasicPanel(this)
                )
        );

        // Remove decorated.display(); and add the decorated panel to this panel
        JPanel decoratedPanel = (JPanel) decorated.getPanel();
        setLayout(new BorderLayout());
        add(decoratedPanel, BorderLayout.CENTER);

        setBackground(new Color(245, 245, 245));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFocusPainted(false);
        logoutButton.setBackground(new Color(231, 76, 60));
        logoutButton.setForeground(Color.WHITE);

        logoutButton.addActionListener(e -> {
            new LogoutCommand(parentFrame).execute();
        });

        add(logoutButton);
    }
}
