package service;

import model.Result;
import java.util.ArrayList;
import java.util.List;

public class ResultService {
    public List<Result> getAllResults() {
        // Dummy implementation, replace with actual DB logic
        return new ArrayList<>();
    }

    public String getQuizTitle(Result result) {
        // Dummy implementation
        return "Quiz Title";
    }

    public String getCourseTitle(Result result) {
        // Dummy implementation
        return "Course Title";
    }

    public double getMaxScore(Result result) {
        // Dummy implementation
        return 100.0;
    }

    public String getDateTaken(Result result) {
        // Dummy implementation
        return result.getDate().toString();
    }

    public List<Result> getResultsByUserId(int userId) {
        // Dummy implementation, replace with actual DB logic
        return new ArrayList<>();
    }
}
