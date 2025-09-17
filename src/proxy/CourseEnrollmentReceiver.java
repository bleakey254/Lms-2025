package proxy;

import model.Enrollment;

public interface CourseEnrollmentReceiver {
    boolean enroll(Enrollment enrollment);
    boolean unenroll(int enrollmentId);
    boolean markCompleted(int enrollmentId);
}
