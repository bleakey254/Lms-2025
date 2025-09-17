package command;

public class SessionsCommand implements AuditorCommand {
    private final AuditorReceiver receiver;

    public SessionsCommand(AuditorReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.showSessions();
        CommandLogger.log("Auditor Sessions Command executed");
    }
}

