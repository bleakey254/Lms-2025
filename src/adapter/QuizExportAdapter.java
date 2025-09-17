package adapter;

import java.util.List;
import model.Quiz;

public interface QuizExportAdapter {
    void export(List<Quiz> quizzes, String filePath) throws Exception;
}

