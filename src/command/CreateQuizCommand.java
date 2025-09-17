package command;

public class CreateQuizCommand implements Command {
    private final TrainerReceiver receiver;

    public CreateQuizCommand(TrainerReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.createQuiz();
    }
}
