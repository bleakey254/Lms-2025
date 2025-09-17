package adapter;

import dao.CourseDAOImpl;
import model.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDAOAdapter implements CourseAdapter {

    private final CourseDAOImpl courseDAO;

    public CourseDAOAdapter(int auditorId) {
        this.courseDAO = new CourseDAOImpl(auditorId);
    }

    @Override
    public Map<String, List<Course>> getCoursesByCategory() {
        try {
            return courseDAO.getCoursesGroupedByCategory();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
