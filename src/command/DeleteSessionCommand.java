package command;

public class DeleteSessionCommand implements Command {
    private final TrainerReceiver receiver;

    public DeleteSessionCommand(TrainerReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.deleteSession();
    }
}
