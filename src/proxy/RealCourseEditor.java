package proxy;

import java.util.logging.Logger;

public class RealCourseEditor implements CourseEditor {
    private static final Logger logger = Logger.getLogger(RealCourseEditor.class.getName());

    @Override
    public void editCourse(int courseId, String newTitle) {
        // Assume this calls the real DB/service
        logger.info("Course " + courseId + " updated to title: " + newTitle);
        // DB update logic here
    }

    @Override
    public void deleteCourse(int courseId) {
        logger.info("Course " + courseId + " deleted.");
        // DB delete logic here
    }
}
