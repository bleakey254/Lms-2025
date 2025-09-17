package composite;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// Composite component for containers
public class CompositeComponent implements UIComponent {
    private JComponent component;
    private List<UIComponent> children = new ArrayList<>();

    public CompositeComponent(JComponent component) {
        this.component = component;
    }

    @Override
    public void add(UIComponent component) {
        children.add(component);
        if (component.getComponent().getParent() != this.component) {
            this.component.add(component.getComponent());
        }
    }

    @Override
    public void remove(UIComponent component) {
        children.remove(component);
        this.component.remove(component.getComponent());
    }

    @Override
    public JComponent getComponent() {
        return component;
    }
}