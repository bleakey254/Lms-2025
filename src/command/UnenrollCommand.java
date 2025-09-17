// command/UnenrollCommand.java
package command;

import model.Enrollment;

public class UnenrollCommand implements EnrollmentCommand {

    private final EnrollmentReceiver receiver;
    private final Enrollment enrollment;

    public UnenrollCommand(EnrollmentReceiver receiver, Enrollment enrollment) {
        this.receiver = receiver;
        this.enrollment = enrollment;
    }

    @Override
    public void execute() {
        receiver.unenroll(enrollment);
    }
}
