package decorator;

import javax.swing.*;

public class TooltipDecorator implements PanelComponent {

    private final PanelComponent component;
    private final String tooltipText;

    public TooltipDecorator(PanelComponent component, String tooltipText) {
        this.component = component;
        this.tooltipText = tooltipText;
    }

    @Override
    public JPanel getPanel() {
        JPanel base = component.getPanel();
        base.setToolTipText(tooltipText);
        return base;
    }

    @Override
    public void applyDecorations() {
        component.applyDecorations();
        // Optionally, you could log or add more decoration logic here
    }
}
