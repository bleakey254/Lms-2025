package facade;

import controller.EnrollmentController;
import model.Enrollment;

import java.util.List;

public class EnrollmentFacade {

    private final EnrollmentController controller;

    public EnrollmentFacade() {
        this.controller = new EnrollmentController();
    }

    public List<Enrollment> getUserEnrollments(int userId) {
        try {
            return controller.getEnrollmentsByUserId(userId);
        } catch (Exception e) {
            System.err.println("❌ Error fetching enrollments: " + e.getMessage());
            return List.of(); // return empty list to avoid null pointer issues
        }
    }

    public boolean unenroll(int enrollmentId) {
        // TODO: Implement actual unenrollment logic using the controller
        // For now, return true to simulate success
        return true;
    }
}
