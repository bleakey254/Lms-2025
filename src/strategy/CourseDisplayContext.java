package strategy;

import model.Course;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class CourseDisplayContext {
    private CourseDisplayStrategy strategy;

    public CourseDisplayContext(CourseDisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(CourseDisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public JPanel renderCourses(Map<String, List<Course>> coursesByCategory) {
        return strategy.generateCourseView(coursesByCategory);
    }
}
