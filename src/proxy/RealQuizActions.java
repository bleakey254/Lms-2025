package proxy;

import command.DeleteQuizCommand;
import command.StartQuizCommand;
import command.SubmitQuizCommand;

import javax.swing.*;

public class RealQuizActions implements QuizActions {
    private final JTable table;

    public RealQuizActions(JTable table) {
        this.table = table;
    }

    @Override
    public void startQuiz() {
        new StartQuizCommand(table).execute();
    }

    @Override
    public void submitQuiz() {
        new SubmitQuizCommand(table).execute();
    }

    @Override
    public void deleteQuiz() {
        new DeleteQuizCommand(table).execute();
    }
}
