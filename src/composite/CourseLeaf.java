package composite;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CourseLeaf extends CourseComponent {
    private final int id;
    private final String description;

    public CourseLeaf(int id, String name, String description) {
        super(name);
        this.id = id;
        this.description = description;
    }

    @Override
    public void display(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{id, getName(), description});
    }

    // Leaf nodes cannot have children
    @Override
    public void add(CourseComponent courseComponent) {
        throw new UnsupportedOperationException("Cannot add to a leaf");
    }

    @Override
    public void remove(CourseComponent courseComponent) {
        throw new UnsupportedOperationException("Cannot remove from a leaf");
    }
}
