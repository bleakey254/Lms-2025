package decorator;

import javax.swing.JPanel;

public abstract class PanelDecorator implements PanelComponent {
    protected final PanelComponent component;

    public PanelDecorator(PanelComponent component) {
        this.component = component;
    }

    @Override
    public void applyDecorations() {
        component.applyDecorations();
    }

    @Override
    public JPanel getPanel() {
        return component.getPanel();
    }
}
