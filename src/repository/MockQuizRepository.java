package repository;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

// Mock quiz data class
class Quiz {
    private int id;
    private String name;
    private String status;

    public Quiz(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

// Mock implementation of QuizRepository
public class MockQuizRepository implements QuizRepository {
    private List<Quiz> quizzes;

    public MockQuizRepository() {
        // Initialize mock data
        quizzes = new ArrayList<>();
        quizzes.add(new Quiz(1, "Math Quiz", "Not Started"));
        quizzes.add(new Quiz(2, "Science Quiz", "Not Started"));
        quizzes.add(new Quiz(3, "History Quiz", "Not Started"));
    }

    @Override
    public DefaultTableModel getQuizTableModel() {
        String[] columns = {"ID", "Name", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Quiz quiz : quizzes) {
            model.addRow(new Object[]{quiz.getId(), quiz.getName(), quiz.getStatus()});
        }
        return model;
    }

    @Override
    public void startQuiz(int quizId) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == quizId) {
                quiz.setStatus("Started");
                break;
            }
        }
    }

    @Override
    public void submitQuiz(int quizId) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == quizId) {
                quiz.setStatus("Submitted");
                break;
            }
        }
    }

    @Override
    public void deleteQuiz(int quizId) {
        quizzes.removeIf(quiz -> quiz.getId() == quizId);
    }
}