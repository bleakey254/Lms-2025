package proxy;

import model.Course;
import observer.Observer;
import javax.swing.JPanel;
import java.util.List;
import java.util.Map;

public interface CourseEnrollmentSubject {
    Map<String, java.util.List<Course>> getCoursesByCategory();
    JPanel renderCourses(Map<String, java.util.List<Course>> courses);
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Object data);
}
