package command;

public class EditCourseCommand implements Command {
    private final TrainerReceiver receiver;

    public EditCourseCommand(TrainerReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.editCourse();
    }
}
