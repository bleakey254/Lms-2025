package view.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class QuizzesPanel extends JPanel {
    private final JTable quizTable;

    public QuizzesPanel() {
        setLayout(new BorderLayout());
        String[] columns = {"Quiz ID", "Title", "Description", "Total Questions"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        quizTable = new JTable(model);
        add(new JScrollPane(quizTable), BorderLayout.CENTER);
    }

    public JTable getQuizTable() {
        return quizTable;
    }
}
