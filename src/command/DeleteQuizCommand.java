package command;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeleteQuizCommand implements Command {
    private final JTable quizTable;

    public DeleteQuizCommand(JTable quizTable) {
        this.quizTable = quizTable;
    }

    @Override
    public void execute() {
        int selectedRow = quizTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) quizTable.getModel();
            model.removeRow(selectedRow);
        }
    }
}
