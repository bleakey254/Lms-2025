package adapter;

import command.TrainerReceiver;

public class TrainerReceiverAdapter implements TrainerActionsAdapter {
    private final TrainerReceiver receiver;

    public TrainerReceiverAdapter(TrainerReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void createCourse() {
        receiver.createCourse();
    }

    @Override
    public void editCourse() {
        receiver.editCourse();
    }

    @Override
    public void deleteCourse() {
        receiver.deleteCourse();
    }

    @Override
    public void createSession() {
        receiver.createSession();
    }

    @Override
    public void editSession() {
        receiver.editSession();
    }

    @Override
    public void deleteSession() {
        receiver.deleteSession();
    }

    @Override
    public void createQuiz() {
        receiver.createQuiz();
    }

    @Override
    public void editQuiz() {
        receiver.editQuiz();
    }
}
