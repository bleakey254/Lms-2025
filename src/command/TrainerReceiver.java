package command;

import javax.swing.JOptionPane;

public class TrainerReceiver {
    public void createCourse() {
        JOptionPane.showMessageDialog(null, "Creating new course...", "Course Creation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void editCourse() {
        JOptionPane.showMessageDialog(null, "Editing course...", "Course Edit", JOptionPane.INFORMATION_MESSAGE);
    }

    public void deleteCourse() {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this course?",
            "Delete Course", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Course deleted successfully", "Course Delete", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void createSession() {
        JOptionPane.showMessageDialog(null, "Creating new session...", "Session Creation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void editSession() {
        JOptionPane.showMessageDialog(null, "Editing session...", "Session Edit", JOptionPane.INFORMATION_MESSAGE);
    }

    public void deleteSession() {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this session?",
            "Delete Session", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Session deleted successfully", "Session Delete", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void createQuiz() {
        JOptionPane.showMessageDialog(null, "Creating new quiz...", "Quiz Creation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void editQuiz() {
        JOptionPane.showMessageDialog(null, "Editing quiz...", "Quiz Edit", JOptionPane.INFORMATION_MESSAGE);
    }
}
