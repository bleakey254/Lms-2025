package adapter;

import model.Course;
import java.util.List;
import java.util.Map;

public interface CourseAdapter {
    Map<String, List<Course>> getCoursesByCategory();
}
