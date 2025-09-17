package proxy;

import facade.CourseManagementFacade;
import model.Course;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseFacadeProxy extends CourseManagementFacade {
    private static final Logger logger = Logger.getLogger(CourseFacadeProxy.class.getName());

    private final CourseManagementFacade realFacade;
    private final int trainerId;

    public CourseFacadeProxy(CourseManagementFacade realFacade, int trainerId) {
        this.realFacade = realFacade;
        this.trainerId = trainerId;
    }

    @Override
    public boolean addCourse(Course course) {
        if (course == null || course.getTitle() == null || course.getTitle().trim().isEmpty()) {
            logger.warning("Add failed: Invalid course title.");
            return false;
        }

        logger.info("Trainer " + trainerId + " is adding course: " + course.getTitle());
        try {
            return realFacade.addCourse(course);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add course", e);
            return false;
        }
    }

    @Override
    public boolean updateCourse(Course course) {
        if (course == null || course.getId() <= 0) {
            logger.warning("Update failed: Invalid course object or ID.");
            return false;
        }

        logger.info("Trainer " + trainerId + " is updating course ID: " + course.getId());
        try {
            return realFacade.updateCourse(course);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update course", e);
            return false;
        }
    }

    @Override
    public boolean deleteCourse(int courseId) {
        if (courseId <= 0) {
            logger.warning("Delete failed: Invalid course ID.");
            return false;
        }

        logger.info("Trainer " + trainerId + " is deleting course ID: " + courseId);
        try {
            return realFacade.deleteCourse(courseId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete course", e);
            return false;
        }
    }

    @Override
    public List<Course> getCoursesByTrainerId(int trainerId) {
        logger.info("Fetching courses for trainer ID: " + trainerId);
        try {
            return realFacade.getCoursesByTrainerId(trainerId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch courses", e);
            return List.of(); // return empty list as fallback
        }
    }
}
