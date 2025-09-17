package repository;

import javax.swing.table.DefaultTableModel;
import java.util.List;

// Interface for quiz data operations (Repository Pattern)
public interface QuizRepository {
    DefaultTableModel getQuizTableModel(); // For populating JTable
    void startQuiz(int quizId);
    void submitQuiz(int quizId);
    void deleteQuiz(int quizId);
}