package command;

public class EditSessionCommand implements Command {
    private final TrainerReceiver receiver;

    public EditSessionCommand(TrainerReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.editSession();
    }
}
