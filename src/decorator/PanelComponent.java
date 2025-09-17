package decorator;

import javax.swing.JPanel;

public interface PanelComponent {
    JPanel getPanel();
    void applyDecorations();
}
