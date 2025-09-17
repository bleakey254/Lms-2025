package command;

import javax.swing.*;

public class SubmitQuizCommand implements QuizCommand {

    private final JTable table;

    public SubmitQuizCommand(JTable table) {
        this.table = table;
    }

    @Override
    public void execute() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String quizName = table.getValueAt(selectedRow, 1).toString();
            JOptionPane.showMessageDialog(table, "✅ Quiz submitted: " + quizName);
            log("SubmitQuizCommand");
        } else {
            JOptionPane.showMessageDialog(table, "⚠️ Please select a quiz to submit.");
        }
    }
}
