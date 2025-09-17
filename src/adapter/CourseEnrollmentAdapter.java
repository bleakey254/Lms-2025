package adapter;

import model.Course;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface CourseEnrollmentAdapter {
    Map<String, List<Course>> getCoursesByCategory();
    JComponent renderCourses(Map<String, List<Course>> coursesByCategory);
    void registerObserver(Object observer);
}
