package view.panels;

import model.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageCoursesPanel extends JPanel {
    private final User trainer;
    private final JTable coursesTable;

    public ManageCoursesPanel(User trainer) {
        this.trainer = trainer;
        setLayout(new BorderLayout());

        String[] columns = {"Course ID", "Title", "Description", "Level", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        coursesTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(coursesTable);
        add(scrollPane, BorderLayout.CENTER);

        setupToolbar();
    }

    private void setupToolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshCourses());
        toolbar.add(refreshButton);

        add(toolbar, BorderLayout.NORTH);
    }

    private void refreshCourses() {
        DefaultTableModel model = (DefaultTableModel) coursesTable.getModel();
        model.setRowCount(0);
        // TODO: Implement actual course data loading
    }
}
