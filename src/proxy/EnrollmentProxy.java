package proxy;

import dao.EnrollmentDao;
import model.Enrollment;
import java.util.List;

public class EnrollmentProxy implements EnrollmentDao {
    private EnrollmentDao realEnrollmentDao;
    private boolean hasAccess;

    public EnrollmentProxy(EnrollmentDao realEnrollmentDao, boolean hasAccess) {
        this.realEnrollmentDao = realEnrollmentDao;
        this.hasAccess = hasAccess;
    }

    @Override
    public List<Enrollment> getEnrollmentsByUserId(int userId) {
        if (!hasAccess) {
            throw new SecurityException("Access denied to get enrollments.");
        }
        return realEnrollmentDao.getEnrollmentsByUserId(userId);
    }

    @Override
    public boolean enrollUser(int userId, int courseId) {
        if (!hasAccess) {
            throw new SecurityException("Access denied to enroll user.");
        }
        return realEnrollmentDao.enrollUser(userId, courseId);
    }

    @Override
    public boolean unenrollUser(int userId, int courseId) {
        if (!hasAccess) {
            throw new SecurityException("Access denied to unenroll user.");
        }
        return realEnrollmentDao.unenrollUser(userId, courseId);
    }
}
