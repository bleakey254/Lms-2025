package decorator;

import javax.swing.JPanel;

public class BasicPanel implements PanelComponent {
    private final JPanel panel;

    public BasicPanel(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void applyDecorations() {
        panel.setBackground(new java.awt.Color(245, 245, 245));
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}
