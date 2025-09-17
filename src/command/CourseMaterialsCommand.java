package command;

public class CourseMaterialsCommand implements AuditorCommand {
    private final AuditorReceiver receiver;

    public CourseMaterialsCommand(AuditorReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.showCourseMaterials();
        CommandLogger.log("Auditor Course Materials Command executed");
    }
}
