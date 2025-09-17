package command;

import view.*;

import javax.swing.*;
import java.awt.CardLayout;

public class AuditorReceiver {

    private final JPanel contentPanel;
    private final JFrame parentFrame;
    private final int auditorId;

    public AuditorReceiver(JPanel contentPanel, JFrame parentFrame, int auditorId) {
        this.contentPanel = contentPanel;
        this.parentFrame = parentFrame;
        this.auditorId = auditorId;
    }

    public void showEnrollCourses() {
        switchPanel(new EnrollCoursesPanel2(auditorId));
    }

    public void showMyEnrollments() {
        switchPanel(new MyEnrollmentsPanel(auditorId));
    }

    public void showCourseMaterials() {
        switchPanel(new CourseMaterialsPanel());
    }

    public void showSessions() {
        switchPanel(new SessionsPanel(new repository.MockSessionRepository(), new strategy.AuditorSessionFilterStrategy()));
    }

    public void showQuizzes() {
        switchPanel(new QuizzesPanel());
    }

    public void showResults() {
        switchPanel(new ResultsPanel());
    }

    public void logout() {
        parentFrame.dispose();
        new view.LoginPageAuditor(parentFrame, new strategy.AuditorLoginStrategy());
    }

    private void switchPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, "MAIN");
        ((CardLayout) contentPanel.getLayout()).show(contentPanel, "MAIN");
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
