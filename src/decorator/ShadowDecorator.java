package decorator;

import javax.swing.*;
import java.awt.*;

public class ShadowDecorator extends PanelDecorator {
    public ShadowDecorator(PanelComponent component) {
        super(component);
    }

    @Override
    public void applyDecorations() {
        super.applyDecorations();
        JPanel panel = getPanel();
        panel.setBorder(BorderFactory.createCompoundBorder(
            panel.getBorder(),
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(3, 3, 3, 3),
                BorderFactory.createLineBorder(new Color(200, 200, 200))
            )
        ));
    }
}
