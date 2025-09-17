package facade;

import model.Auditor;
import strategy.AuditorSessionFilterStrategy;
import view.*;

import javax.swing.*;
import java.awt.*;

public class AuditorDashboardFacade {

    private final Auditor auditor;

    public AuditorDashboardFacade(Auditor auditor) {
        this.auditor = auditor;
    }

    public JLabel createGreetingLabel() {
        String name = auditor.getFirstName() + " " + auditor.getLastName();
        JLabel greeting = new JLabel("Welcome, " + name + "!", JLabel.CENTER);
        greeting.setFont(new Font("Segoe UI", Font.BOLD, 20));
        greeting.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return greeting;
    }

    public JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tabbedPane.addTab("Enroll Courses", new EnrollCoursesPanel2(auditor.getId()));
        tabbedPane.addTab("My Enrollments", new MyEnrollmentsPanel(auditor.getId()));
        tabbedPane.addTab("Course Materials", new CourseMaterialsPanel());
        tabbedPane.addTab("Sessions", new SessionsPanel(new repository.MockSessionRepository(), new AuditorSessionFilterStrategy()));
        tabbedPane.addTab("Quizzes", new QuizzesPanel());
        tabbedPane.addTab("Results", new ResultsPanel());

        return tabbedPane;
    }
}
