package repository;

import model.Course;
import javax.swing.table.DefaultTableModel;
import java.util.Map;
import java.util.List;

// Interface for course data operations (Repository Pattern)
public interface CourseRepository {
    DefaultTableModel getCourseTableModel(int auditorId); // For populating JTable with courses
    void enrollCourse(int auditorId, int courseId);
    void addCourse(Course course);
    Map<String, List<Course>> getCoursesByCategory(); // Added for CoursePanel
}