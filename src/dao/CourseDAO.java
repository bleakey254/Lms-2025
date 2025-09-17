package dao;

import model.Course;
import java.util.List;
import java.util.Map;

public interface CourseDAO {
    Map<String, List<Course>> getCoursesGroupedByCategory();
    List<Course> getAllCourses();
    Course getCourseById(int id);
    boolean addCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(int id);
}
