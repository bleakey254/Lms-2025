package command;

public class EditQuizCommand implements Command {
    private final TrainerReceiver receiver;

    public EditQuizCommand(TrainerReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.editQuiz();
    }
}
