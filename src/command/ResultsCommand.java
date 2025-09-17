package command;

public class ResultsCommand implements AuditorCommand {
    private final AuditorReceiver receiver;

    public ResultsCommand(AuditorReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.showResults();
        CommandLogger.log("Auditor Results Command executed");
    }
}
