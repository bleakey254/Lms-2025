package command;

public class MyEnrollmentsCommand implements AuditorCommand {
    private final AuditorReceiver receiver;

    public MyEnrollmentsCommand(AuditorReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.showMyEnrollments();
        CommandLogger.log("Auditor My Enrollments Command executed");
    }
}
