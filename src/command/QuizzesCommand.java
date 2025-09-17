package command;

public class QuizzesCommand implements AuditorCommand {
    private final AuditorReceiver receiver;

    public QuizzesCommand(AuditorReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.showQuizzes();
        CommandLogger.log("Auditor Quizzes Command executed");
    }
}

