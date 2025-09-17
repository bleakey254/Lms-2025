package composite;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class CourseGroup extends CourseComponent {
    private final List<CourseComponent> children = new ArrayList<>();

    public CourseGroup(String groupName) {
        super(groupName);
    }

    public void add(CourseComponent component) {
        children.add(component);
    }

    public void remove(CourseComponent component) {
        children.remove(component);
    }

    // Removed @Override because this does not override a superclass method
    public void display(DefaultTableModel model) {
        for (CourseComponent child : children) {
            if (child instanceof CourseGroup) {
                ((CourseGroup) child).display(model);
            } else if (child != null) {
                // If child is a Lesson or other leaf, call its display(DefaultTableModel)
                try {
                    child.getClass().getMethod("display", DefaultTableModel.class)
                        .invoke(child, model);
                } catch (Exception e) {
                    // Handle gracefully if method does not exist
                }
            }
        }
    }

    @Override
    public void display(javax.swing.JTable table) {
        // Optionally implement this if you want to support the composite pattern's default method
        // For now, just call the superclass implementation
        super.display(table);
    }
}
