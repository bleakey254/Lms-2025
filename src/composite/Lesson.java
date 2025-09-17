package composite;

import javax.swing.table.DefaultTableModel;

public class Lesson extends CourseComponent {
    private final String id;
    private final String title;
    private final String description;

    public Lesson(String id, String title, String description) {
        super(title); // Pass title as the name to the superclass
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void display(DefaultTableModel model) {
        model.addRow(new Object[]{id, title, description});
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void add(CourseComponent courseComponent) {
        throw new UnsupportedOperationException("Cannot add to a Lesson (leaf)");
    }

    @Override
    public void remove(CourseComponent courseComponent) {
        throw new UnsupportedOperationException("Cannot remove from a Lesson (leaf)");
    }
}
