package command;

public class AuditorLogoutCommand implements AuditorCommand {

    private final AuditorReceiver receiver;

    public AuditorLogoutCommand(AuditorReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.logout();
        CommandLogger.log("Auditor Logout Command executed");
    }
}
