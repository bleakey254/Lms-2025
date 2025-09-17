package adapter;

import facade.EnrollCoursesFacade;
import model.Course;
import observer.Observer;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class FacadeCourseEnrollmentAdapter implements CourseEnrollmentAdapter {

    private final EnrollCoursesFacade facade;

    public FacadeCourseEnrollmentAdapter(int auditorId) {
        this.facade = new EnrollCoursesFacade(auditorId);
    }

    @Override
    public Map<String, List<Course>> getCoursesByCategory() {
        return facade.getCoursesByCategory();
    }

    @Override
    public JComponent renderCourses(Map<String, List<Course>> coursesByCategory) {
        return facade.renderCourses(coursesByCategory);
    }

    public void registerObserver(Observer observer) {
        facade.registerObserver(observer);
    }

    public void registerObserver(Object observer) {
        if (observer instanceof Observer) {
            facade.registerObserver((Observer) observer);
        } else {
            throw new IllegalArgumentException("Observer must implement observer.Observer");
        }
    }
}
