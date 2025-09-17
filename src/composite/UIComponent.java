package composite;

import javax.swing.*;

// Interface for UI components (Composite Pattern)
public interface UIComponent {
    void add(UIComponent component);
    void remove(UIComponent component);
    JComponent getComponent();
}