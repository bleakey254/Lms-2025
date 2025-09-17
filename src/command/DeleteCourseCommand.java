package command;

public class DeleteCourseCommand implements Command {
    private final TrainerReceiver receiver;

    public DeleteCourseCommand(TrainerReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.deleteCourse();
    }
}
