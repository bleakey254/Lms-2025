package adapter;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import model.Quiz;

public class CSVQuizExportAdapter implements QuizExportAdapter {
    @Override
    public void export(List<Quiz> quizzes, String filePath) throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Quiz ID,Quiz Title,Total Questions");
            for (Quiz quiz : quizzes) {
                writer.printf("%s,%s,%d\n",
                        quiz.getId(),
                        quiz.getTitle().replace(",", " "),
                        quiz.getQuestions().size());
            }
        }
    }
}
