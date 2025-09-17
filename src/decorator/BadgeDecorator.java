package decorator;

import javax.swing.*;
import java.awt.*;

public class BadgeDecorator implements PanelComponent {

    private final PanelComponent component;
    private final String badgeText;

    public BadgeDecorator(PanelComponent component, String badgeText) {
        this.component = component;
        this.badgeText = badgeText;
    }

    @Override
    public JPanel getPanel() {
        JPanel container = new JPanel(new BorderLayout());
        container.add(component.getPanel(), BorderLayout.CENTER);

        JLabel badge = new JLabel(badgeText);
        badge.setOpaque(true);
        badge.setBackground(Color.RED);
        badge.setForeground(Color.WHITE);
        badge.setFont(new Font("Arial", Font.BOLD, 10));
        badge.setHorizontalAlignment(SwingConstants.CENTER);
        badge.setPreferredSize(new Dimension(60, 20));

        JPanel badgeWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        badgeWrapper.add(badge);
        badgeWrapper.setOpaque(false);

        container.add(badgeWrapper, BorderLayout.NORTH);
        return container;
    }

    @Override
    public void applyDecorations() {
        component.applyDecorations();
        // Optionally, you could add more decoration logic here for the badge
    }
}
