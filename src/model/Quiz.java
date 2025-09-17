package model;

import java.util.List;

public class Quiz {
    private String id;
    private String title;
    private List<String> questions;

    public Quiz(String id, String title, List<String> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getQuestions() {
        return questions;
    }
}

