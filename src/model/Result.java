package model;

import java.util.Date;

public class Result {
    private int id;
    private int userId;
    private int quizId;
    private double score;
    private Date date;
    private String quizTitle;
    private String courseTitle;
    private double maxScore;

    public Result(int id, int userId, int quizId, double score, Date date, String quizTitle, String courseTitle, double maxScore) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.date = date;
        this.quizTitle = quizTitle;
        this.courseTitle = courseTitle;
        this.maxScore = maxScore;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getQuizId() {
        return quizId;
    }

    public double getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public String getDateTaken() {
        return date != null ? date.toString() : "";
    }
}
