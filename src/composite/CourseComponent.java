package composite;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CourseComponent {
    protected String name;
    protected List<CourseComponent> children = new ArrayList<>();

    public CourseComponent(String name) {
        this.name = name;
    }

    public CourseComponent getChild(int i) {
        return children.get(i);
    }

    public String getName() {
        return name;
    }

    public void display(JTable table) {
        for (CourseComponent component : children) {
            component.display(table);
        }
    }

    public List<CourseComponent> getChildren() {
        return children;
    }

    public abstract void add(CourseComponent courseComponent);
    public abstract void remove(CourseComponent courseComponent);
}
