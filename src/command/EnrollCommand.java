// command/EnrollCommand.java
package command;

import facade.EnrollCoursesFacade;
import model.Course;

public class EnrollCommand implements EnrollmentCommand {

    private final EnrollCoursesFacade facade;
    private final Course course;
    private final int auditorId;

    public EnrollCommand(EnrollCoursesFacade facade, Course course, int auditorId) {
        this.facade = facade;
        this.course = course;
        this.auditorId = auditorId;
    }

    @Override
    public void execute() {
        facade.enrollInCourse(course.getId());
    }
}
