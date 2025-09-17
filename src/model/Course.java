package model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Course {
    private int id;
    private int trainerId;
    private String name;
    private String level;
    private double cost;
    private String description;
    private String categoryName;
    private Date startDate;
    private Date endDate;
    private Date createdAt;

    public Course(int id, int trainerId, String name, String level, double cost,
                  String description, String categoryName, Date startDate, Date endDate, Date createdAt) {
        this.id = id;
        this.trainerId = trainerId;
        this.name = name;
        this.level = level;
        this.cost = cost;
        this.description = description;
        this.categoryName = categoryName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
    }

    // Getters
    public int getId() { return id; }
    public int getTrainerId() { return trainerId; }
    public String getName() { return name; }
    public String getLevel() { return level; }
    public double getCost() { return cost; }
    public String getDescription() { return description; }
    public String getCategory() { return categoryName; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public Date getCreatedAt() { return createdAt; }
    public String getTitle() {
        return name;
    }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setLevel(String level) { this.level = level; }
    public void setCost(double cost) { this.cost = cost; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String categoryName) { this.categoryName = categoryName; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public static Map<String, List<Course>> getCoursesGroupedByCategory() {
        // Dummy implementation, replace with actual DB logic
        return new java.util.HashMap<>();
    }

    public static void enrollAuditorToCourse(int courseId, int auditorId) {
        // Dummy implementation, replace with actual enrollment logic
    }
}
