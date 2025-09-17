// command/EnrollmentReceiver.java
package command;

import model.Enrollment;
import facade.EnrollmentFacade;

import javax.swing.*;

public class EnrollmentReceiver {

    private final EnrollmentFacade facade = new EnrollmentFacade();

    public void unenroll(Enrollment enrollment) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to unenroll from course ID " + enrollment.getCourseId() + "?",
                "Confirm Unenroll", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = facade.unenroll(enrollment.getId());
            if (success) {
                JOptionPane.showMessageDialog(null, "Unenrolled successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to unenroll. Try again.");
            }
        }
    }
}
