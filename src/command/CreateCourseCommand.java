package command;

public class CreateCourseCommand implements Command {
    private final TrainerReceiver receiver;

    public CreateCourseCommand(TrainerReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.createCourse();
    }
}
