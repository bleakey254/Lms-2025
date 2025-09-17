package adapter;

import model.Enrollment;

import java.util.List;

public interface EnrollmentAdapter {
    List<Enrollment> getUserEnrollments(int userId);
}

