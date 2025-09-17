package model;

import java.time.LocalDate;

public class Enrollment {
    private final int id;
    private final int userId;
    private final int courseId;
    private final LocalDate enrolledDate;
    private String status;

    public Enrollment(int id, int userId, int courseId, LocalDate enrolledDate) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.enrolledDate = enrolledDate;
        this.status = "Enrolled"; // Default status
    }

    public Enrollment(int id, int userId, int courseId, LocalDate enrolledDate, String status) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.enrolledDate = enrolledDate;
        this.status = status != null ? status : "Enrolled";
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getCourseId() { return courseId; }
    public LocalDate getEnrolledDate() { return enrolledDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
