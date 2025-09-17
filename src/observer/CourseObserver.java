package observer;

import model.Course;
import java.util.List;
import java.util.Map;

public interface CourseObserver {
    void onCourseSelected(Course course);
    void update(Map<String, List<Course>> courses);
}
