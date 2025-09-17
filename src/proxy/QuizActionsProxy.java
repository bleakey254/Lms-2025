package proxy;

import facade.QuizManagementFacade;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class QuizActionsProxy implements QuizActions {
    private JTable quizzesTable;
    private QuizManagementFacade facade;

    public QuizActionsProxy(JTable quizzesTable, QuizManagementFacade facade) {
        this.quizzesTable = quizzesTable;
        this.facade = facade;
    }

    @Override
    public void startQuiz() {
        int selectedRow = quizzesTable.getSelectedRow();
        if (selectedRow >= 0) {
            int quizId = (int) quizzesTable.getValueAt(selectedRow, 0);
            facade.startQuiz(quizId);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(quizzesTable, "Please select a quiz to start.");
        }
    }

    @Override
    public void submitQuiz() {
        int selectedRow = quizzesTable.getSelectedRow();
        if (selectedRow >= 0) {
            int quizId = (int) quizzesTable.getValueAt(selectedRow, 0);
            facade.submitQuiz(quizId);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(quizzesTable, "Please select a quiz to submit.");
        }
    }

    @Override
    public void deleteQuiz() {
        int selectedRow = quizzesTable.getSelectedRow();
        if (selectedRow >= 0) {
            int quizId = (int) quizzesTable.getValueAt(selectedRow, 0);
            facade.deleteQuiz(quizId);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(quizzesTable, "Please select a quiz to delete.");
        }
    }

    private void refreshTable() {
        DefaultTableModel model = facade.getQuizTableModel();
        quizzesTable.setModel(model);
    }
}