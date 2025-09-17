package facade;

import controller.CourseController;
import model.Course;

import java.util.List;

public class CourseManagementFacade {

    private final CourseController courseController;

    public CourseManagementFacade() {
        this.courseController = new CourseController();
    }

    public List<Course> getCoursesByTrainerId(int trainerId) {
        return courseController.getCoursesByTrainerId(trainerId);
    }

    public boolean addCourse(Course course) {
        return courseController.addCourse(course);
    }

    public boolean updateCourse(Course course) {
        return courseController.updateCourse(course);
    }

    public boolean deleteCourse(int courseId) {
        try {
            return courseController.deleteCourse(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Course getCourseById(int courseId) {
        try {
            return courseController.getCourseById(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
