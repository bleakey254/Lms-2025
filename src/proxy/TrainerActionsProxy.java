package proxy;

import adapter.TrainerActionsAdapter;
import adapter.TrainerReceiverAdapter;
import command.TrainerReceiver;
import model.Trainer;
import session.SessionManager;
import javax.swing.JOptionPane;

public class TrainerActionsProxy implements TrainerActionsAdapter {
    private TrainerActionsAdapter realAdapter;

    private boolean checkAccess() {
        if (!(SessionManager.getInstance().getCurrentUser() instanceof Trainer)) {
            JOptionPane.showMessageDialog(null, "Access denied: Not logged in as Trainer",
                                       "Security Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void initialize() {
        if (realAdapter == null) {
            realAdapter = new TrainerReceiverAdapter(new TrainerReceiver());
        }
    }

    @Override
    public void createCourse() {
        if (checkAccess()) {
            initialize();
            realAdapter.createCourse();
        }
    }

    @Override
    public void editCourse() {
        if (checkAccess()) {
            initialize();
            realAdapter.editCourse();
        }
    }

    @Override
    public void deleteCourse() {
        if (checkAccess()) {
            initialize();
            realAdapter.deleteCourse();
        }
    }

    @Override
    public void createSession() {
        if (checkAccess()) {
            initialize();
            realAdapter.createSession();
        }
    }

    @Override
    public void editSession() {
        if (checkAccess()) {
            initialize();
            realAdapter.editSession();
        }
    }

    @Override
    public void deleteSession() {
        if (checkAccess()) {
            initialize();
            realAdapter.deleteSession();
        }
    }

    @Override
    public void createQuiz() {
        if (checkAccess()) {
            initialize();
            realAdapter.createQuiz();
        }
    }

    @Override
    public void editQuiz() {
        if (checkAccess()) {
            initialize();
            realAdapter.editQuiz();
        }
    }
}
