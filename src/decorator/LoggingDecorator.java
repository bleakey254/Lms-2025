package decorator;

import javax.swing.*;
import java.time.LocalDateTime;

public class LoggingDecorator implements PanelComponent {

    private final PanelComponent component;

    public LoggingDecorator(PanelComponent component) {
        this.component = component;
    }

    @Override
    public JPanel getPanel() {
        System.out.println("[LOG] Panel rendered at: " + LocalDateTime.now());
        return component.getPanel();
    }

    @Override
    public void applyDecorations() {
        System.out.println("[LOG] Decorations applied at: " + LocalDateTime.now());
        component.applyDecorations();
    }
}
