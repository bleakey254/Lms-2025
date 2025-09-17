package proxy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseEditorProxy implements CourseEditor {
    private static final Logger logger = Logger.getLogger(CourseEditorProxy.class.getName());

    private final RealCourseEditor realEditor = new RealCourseEditor();
    private final String userRole;
    private final int userId;

    public CourseEditorProxy(String userRole, int userId) {
        this.userRole = userRole;
        this.userId = userId;
    }

    @Override
    public void editCourse(int courseId, String newTitle) {
        if (!"trainer".equalsIgnoreCase(userRole)) {
            logger.warning("User " + userId + " attempted to edit course " + courseId + " without permission.");
            return;
        }

        if (courseId <= 0 || newTitle == null || newTitle.trim().isEmpty()) {
            logger.warning("Invalid course edit request by user " + userId);
            return;
        }

        logger.info("Trainer " + userId + " editing course ID " + courseId + " to title: " + newTitle);
        try {
            realEditor.editCourse(courseId, newTitle);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to edit course ID " + courseId, e);
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        if (!"trainer".equalsIgnoreCase(userRole)) {
            logger.warning("User " + userId + " attempted to delete course " + courseId + " without permission.");
            return;
        }

        if (courseId <= 0) {
            logger.warning("Invalid course delete request by user " + userId);
            return;
        }

        logger.info("Trainer " + userId + " deleting course ID " + courseId);
        try {
            realEditor.deleteCourse(courseId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete course ID " + courseId, e);
        }
    }
}
