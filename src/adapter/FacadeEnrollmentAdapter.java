package adapter;

import facade.EnrollmentFacade;
import model.Enrollment;

import java.util.List;

public class FacadeEnrollmentAdapter implements EnrollmentAdapter {

    private final EnrollmentFacade facade;

    public FacadeEnrollmentAdapter() {
        this.facade = new EnrollmentFacade();
    }

    @Override
    public List<Enrollment> getUserEnrollments(int userId) {
        return facade.getUserEnrollments(userId);
    }
}
