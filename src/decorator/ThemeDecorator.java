package decorator;

import javax.swing.*;
import java.awt.*;

public class ThemeDecorator implements PanelComponent {

    private final PanelComponent component;
    private final Color background;
    private final Color foreground;

    public ThemeDecorator(PanelComponent component, Color background, Color foreground) {
        this.component = component;
        this.background = background;
        this.foreground = foreground;
    }

    @Override
    public JPanel getPanel() {
        JPanel panel = component.getPanel();
        panel.setBackground(background);
        panel.setForeground(foreground);
        return panel;
    }

    @Override
    public void applyDecorations() {
        component.applyDecorations();
        JPanel panel = component.getPanel();
        panel.setBackground(background);
        panel.setForeground(foreground);
    }
}
