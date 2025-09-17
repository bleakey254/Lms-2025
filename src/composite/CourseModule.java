package composite;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class CourseModule extends CourseComponent {
    public CourseModule(String moduleTitle) {
        super(moduleTitle);
    }

    @Override
    public void display(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{getName()});
        for (CourseComponent component : getChildren()) {
            component.display(table);
        }
    }

    @Override
    public void add(CourseComponent courseComponent) {
        children.add(courseComponent);
    }

    @Override
    public void remove(CourseComponent courseComponent) {
        children.remove(courseComponent);
    }
}
