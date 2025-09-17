package command;

import javax.swing.*;

public class StartQuizCommand implements QuizCommand {

    private final JTable table;

    public StartQuizCommand(JTable table) {
        this.table = table;
    }

    @Override
    public void execute() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String quizName = table.getValueAt(selectedRow, 1).toString();
            JOptionPane.showMessageDialog(table, "🎯 Starting quiz: " + quizName);
            log("StartQuizCommand");
        } else {
            JOptionPane.showMessageDialog(table, "⚠️ Please select a quiz to start.");
        }
    }
}
