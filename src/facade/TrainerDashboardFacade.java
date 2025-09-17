package facade;

import model.Trainer;
import strategy.TrainerSessionFilterStrategy;
import view.*;

import javax.swing.*;
import java.awt.*;

public class TrainerDashboardFacade {

    private final Trainer trainer;

    public TrainerDashboardFacade(Trainer trainer) {
        this.trainer = trainer;
    }

    public JPanel createSidebar(JPanel contentPanel, JFrame frame) {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(44, 62, 80));
        sidebar.setPreferredSize(new Dimension(200, frame.getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JButton manageCoursesBtn = createSidebarButton("Manage Courses");
        JButton courseMaterialsBtn = createSidebarButton("Course Materials");
        JButton scheduleSessionsBtn = createSidebarButton("Schedule Sessions");
        JButton quizzesBtn = createSidebarButton("Quizzes");
        JButton resultsBtn = createSidebarButton("Results");

        sidebar.add(manageCoursesBtn);
        sidebar.add(courseMaterialsBtn);
        sidebar.add(scheduleSessionsBtn);
        sidebar.add(quizzesBtn);
        sidebar.add(resultsBtn);

        // Action Listeners using switchPanel
        manageCoursesBtn.addActionListener(e -> switchPanel(contentPanel, new ManageCoursesPanel(trainer)));
        courseMaterialsBtn.addActionListener(e -> switchPanel(contentPanel, new CourseMaterialsPanel()));
        scheduleSessionsBtn.addActionListener(e -> switchPanel(contentPanel, new SessionsPanel(new repository.MockSessionRepository(), new TrainerSessionFilterStrategy())));
        quizzesBtn.addActionListener(e -> switchPanel(contentPanel, new QuizzesPanel()));
        resultsBtn.addActionListener(e -> switchPanel(contentPanel, new ResultsPanel()));

        return sidebar;
    }

    public JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel greetingsLabel = new JLabel("Welcome, " + trainer.getFirstName() + "!", JLabel.CENTER);
        greetingsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        greetingsLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        contentPanel.add(greetingsLabel, BorderLayout.NORTH);

        return contentPanel;
    }

    public void switchPanel(JPanel contentPanel, JPanel newPanel) {
        contentPanel.removeAll();
        contentPanel.add(newPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setBackground(new Color(52, 73, 94));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }
}
