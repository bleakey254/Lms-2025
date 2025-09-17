package proxy;

import dao.CourseDAO;
import model.Course;
import java.util.List;
import java.util.Map;

public class CourseProxy implements CourseDAO {
    private CourseDAO realCourseDao;
    private boolean hasAccess;

    public CourseProxy(CourseDAO realCourseDao, boolean hasAccess) {
        this.realCourseDao = realCourseDao;
        this.hasAccess = hasAccess;
    }

    @Override
    public List<Course> getAllCourses() {
        if (!hasAccess) {
            throw new SecurityException("Access denied to get all courses.");
        }
        return realCourseDao.getAllCourses();
    }

    @Override
    public Course getCourseById(int id) {
        if (!hasAccess) {
            throw new SecurityException("Access denied to get course by id.");
        }
        return realCourseDao.getCourseById(id);
    }

    @Override
    public boolean addCourse(Course course) {
        if (!hasAccess) {
            throw new SecurityException("Access denied to add course.");
        }
        return realCourseDao.addCourse(course);
    }

    @Override
    public boolean updateCourse(Course course) {
        if (!hasAccess) {
            throw new SecurityException("Access denied to update course.");
        }
        return realCourseDao.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(int id) {
        if (!hasAccess) {
            throw new SecurityException("Access denied to delete course.");
        }
        return realCourseDao.deleteCourse(id);
    }

    @Override
    public Map<String, List<Course>> getCoursesGroupedByCategory() {
        if (!hasAccess) {
            throw new SecurityException("Access denied to get courses grouped by category.");
        }
        return realCourseDao.getCoursesGroupedByCategory();
    }
}
