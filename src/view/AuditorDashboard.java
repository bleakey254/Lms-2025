package view;

import adapter.AuditorActionsAdapter;
import command.*;
import decorator.BorderDecorator;
import decorator.ShadowDecorator;
import decorator.PanelComponent;
import decorator.BasicPanel;
import model.Auditor;
import proxy.AuditorActionsProxy;
import session.SessionManager;
import strategy.AuditorSessionFilterStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class AuditorDashboard extends JFrame {

    private final JPanel contentPanel;
    private final CardLayout cardLayout;
    private final Map<String, Command> commands = new LinkedHashMap<>();

    public AuditorDashboard() {
        setTitle("Auditor Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        Auditor auditor = (Auditor) SessionManager.getInstance().getCurrentUser();
        if (auditor == null) {
            JOptionPane.showMessageDialog(this, "No auditor is currently logged in!", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        // 🔐 Use proxy to protect adapter actions
        AuditorActionsProxy adapter = new AuditorActionsProxy(
            new AuditorReceiver(contentPanel, this, auditor.getId()),
            contentPanel,
            cardLayout
        );

        // Panels with Decorators
        JComponent homePanel = createDecoratedPanel(createHomePanel(auditor));
        JComponent enrollPanel = createDecoratedPanel(new EnrollCoursesPanel2(auditor.getId()));
        JComponent myEnrollmentsPanel = createDecoratedPanel(new MyEnrollmentsPanel(auditor.getId()));
        JComponent materialsPanel = createDecoratedPanel(new CourseMaterialsPanel());
        JComponent sessionsPanel = createDecoratedPanel(new SessionsPanel(new repository.MockSessionRepository(), new strategy.AuditorSessionFilterStrategy()));
        JComponent quizzesPanel = createDecoratedPanel(new QuizzesPanel());
        JComponent resultsPanel = createDecoratedPanel(new ResultsPanel());

        contentPanel.add(homePanel, "Home");
        contentPanel.add(enrollPanel, "Enroll Courses");
        contentPanel.add(myEnrollmentsPanel, "My Enrollments");
        contentPanel.add(materialsPanel, "Course Materials");
        contentPanel.add(sessionsPanel, "Sessions");
        contentPanel.add(quizzesPanel, "Quizzes");
        contentPanel.add(resultsPanel, "Results");

        // Commands using protected adapter
        commands.put("Home", new HomeCommand(contentPanel, (JPanel) homePanel));
        commands.put("Enroll Courses", adapter.createPanelCommand("Enroll Courses"));
        commands.put("My Enrollments", adapter.createPanelCommand("My Enrollments"));
        commands.put("Course Materials", adapter.createPanelCommand("Course Materials"));
        commands.put("Sessions", adapter.createPanelCommand("Sessions"));
        commands.put("Quizzes", adapter.createPanelCommand("Quizzes"));
        commands.put("Results", adapter.createPanelCommand("Results"));

        addSidebar(commands);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        commands.get("Home").execute(); // Show home by default
        setVisible(true);
    }

    private JPanel createHomePanel(Auditor auditor) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcome = new JLabel("Welcome, " + auditor.getFirstName() + " " + auditor.getLastName(), JLabel.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(welcome, BorderLayout.CENTER);
        return panel;
    }

    private JComponent createDecoratedPanel(JPanel rawPanel) {
        PanelComponent base = new BasicPanel(rawPanel);
        PanelComponent decorated = new ShadowDecorator(new BorderDecorator(base));
        return decorated.getPanel();
    }

    private void addSidebar(Map<String, Command> commands) {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(52, 73, 94));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            JButton button = new JButton(entry.getKey());
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            button.setFocusPainted(false);
            button.setBackground(new Color(52, 152, 219));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.addActionListener(e -> entry.getValue().execute());
            sidebar.add(Box.createVerticalStrut(10));
            sidebar.add(button);
        }

        getContentPane().add(sidebar, BorderLayout.WEST);
    }
}
