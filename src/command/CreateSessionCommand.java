package command;

public class CreateSessionCommand implements Command {
    private final TrainerReceiver receiver;

    public CreateSessionCommand(TrainerReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.createSession();
    }
}
