package model;

public class CourseMaterial {
    private int id;
    private String title;
    private String filePath;
    private String description;
    private String content;

    public CourseMaterial(int id, String title, String filePath, String description, String content) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.description = description;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }
}
