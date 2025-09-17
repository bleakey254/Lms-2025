package decorator;

import javax.swing.*;
import java.awt.*;

public class BorderDecorator extends PanelDecorator {
    public BorderDecorator(PanelComponent component) {
        super(component);
    }

    @Override
    public void applyDecorations() {
        super.applyDecorations();
        JPanel panel = getPanel();
        panel.setBorder(BorderFactory.createCompoundBorder(
            panel.getBorder(),
            BorderFactory.createLineBorder(Color.BLACK)
        ));
    }
}
