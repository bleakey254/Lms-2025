package dao;

import java.util.List;
import model.Enrollment;

public interface EnrollmentDao {
    List<Enrollment> getEnrollmentsByUserId(int userId);
    boolean enrollUser(int userId, int courseId);
    boolean unenrollUser(int userId, int courseId);
}
