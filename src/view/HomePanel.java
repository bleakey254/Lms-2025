package view;

import decorator.BasicPanel;
import decorator.BorderDecorator;
import decorator.PanelComponent;
import decorator.ShadowDecorator;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel(String role) {
        // Apply decorators
        PanelComponent decorated = new ShadowDecorator(
                new BorderDecorator(
                        new BasicPanel(this)
                )
        );

        JLabel welcome = new JLabel("🏠 Welcome to your " + role + " Dashboard", SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        // Add the welcome label to the decorated panel
        JPanel decoratedPanel = (JPanel) decorated.getPanel();
        decoratedPanel.add(welcome, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(decoratedPanel, BorderLayout.CENTER);
    }
}
