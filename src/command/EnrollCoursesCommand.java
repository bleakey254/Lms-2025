package command;

public class EnrollCoursesCommand implements AuditorCommand {
    private final AuditorReceiver receiver;

    public EnrollCoursesCommand(AuditorReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.showEnrollCourses();
        CommandLogger.log("Auditor Enroll Courses Command executed");
    }
}

