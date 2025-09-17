package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class QuizzesPanel extends JPanel {
    private final JTable quizTable;

    public QuizzesPanel() {
        setLayout(new BorderLayout());

        // Initialize quiz table with columns
        String[] columns = {"Quiz ID", "Title", "Description", "Duration"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        quizTable = new JTable(model);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(quizTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getQuizTable() {
        return quizTable;
    }
}
