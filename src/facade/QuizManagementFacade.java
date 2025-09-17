package facade;

import repository.QuizRepository;
import javax.swing.table.DefaultTableModel;

public class QuizManagementFacade {
    private QuizRepository quizRepository;

    public QuizManagementFacade(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public DefaultTableModel getQuizTableModel() {
        return quizRepository.getQuizTableModel();
    }

    // Delegate quiz actions to repository
    public void startQuiz(int quizId) {
        quizRepository.startQuiz(quizId);
    }

    public void submitQuiz(int quizId) {
        quizRepository.submitQuiz(quizId);
    }

    public void deleteQuiz(int quizId) {
        quizRepository.deleteQuiz(quizId);
    }
}