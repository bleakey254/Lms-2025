package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Session {
    private int id;
    private String title;
    private String description;
    private int courseId;
    private Date scheduledDateTime;
    private boolean completed;
    private final Set<Integer> participants;

    public Session(int id, String title, String description, int courseId, Date scheduledDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.courseId = courseId;
        this.scheduledDateTime = scheduledDateTime;
        this.completed = false;
        this.participants = new HashSet<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public Date getScheduledDateTime() { return scheduledDateTime; }
    public void setScheduledDateTime(Date scheduledDateTime) { this.scheduledDateTime = scheduledDateTime; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public void addParticipant(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        participants.add(userId);
    }

    public void removeParticipant(int userId) {
        participants.remove(userId);
    }

    public Set<Integer> getParticipants() {
        return new HashSet<>(participants);
    }

    public boolean hasParticipant(int userId) {
        return participants.contains(userId);
    }
}
