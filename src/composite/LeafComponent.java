package composite;

import javax.swing.*;

// Leaf component for individual UI elements
public class LeafComponent implements UIComponent {
    private JComponent component;

    public LeafComponent(JComponent component) {
        this.component = component;
    }

    @Override
    public void add(UIComponent component) {
        throw new UnsupportedOperationException("Leaf cannot add components");
    }

    @Override
    public void remove(UIComponent component) {
        throw new UnsupportedOperationException("Leaf cannot remove components");
    }

    @Override
    public JComponent getComponent() {
        return component;
    }
}