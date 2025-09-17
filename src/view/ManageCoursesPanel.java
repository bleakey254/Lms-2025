package view;

import model.User;
import javax.swing.*;
import java.awt.*;

public class ManageCoursesPanel extends JPanel {
    private final User user;
    private final JTable coursesTable;

    public ManageCoursesPanel(User user) {
        this.user = user;
        setLayout(new BorderLayout());

        // Initialize courses table
        String[] columns = {"Course ID", "Title", "Description", "Level"};
        coursesTable = new JTable(new Object[0][4], columns);

        JScrollPane scrollPane = new JScrollPane(coursesTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add toolbar with actions
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        add(toolbar, BorderLayout.NORTH);
    }

    public JTable getCoursesTable() {
        return coursesTable;
    }
}
