package strategy;

import model.Course;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface CourseDisplayStrategy {
    JPanel generateCourseView(Map<String, List<Course>> coursesByCategory);
}
