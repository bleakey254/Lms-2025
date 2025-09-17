package view;

import model.DBConnection;
import adapter.TrainerActionsAdapter;
import command.*;
import decorator.*;
import model.*;
import proxy.TrainerActionsProxy;
import repository.MockSessionRepository;
import session.SessionManager;
import strategy.TrainerSessionFilterStrategy;
import view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

public class TrainerDashboard extends JFrame {
    private static final Logger logger = Logger.getLogger(TrainerDashboard.class.getName());
    private final JPanel contentPanel;
    private final CardLayout cardLayout;
    private final Map<String, JPanel> panelMap;
    private TrainerActionsAdapter adapter;
    private QuizzesPanel quizzesPanelRef;
    private JPanel sidebar;

    public TrainerDashboard() {
        setTitle("Trainer Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        panelMap = new LinkedHashMap<>();

        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (!(currentUser instanceof Trainer)) {
            logger.severe("No trainer is logged in!");
            JOptionPane.showMessageDialog(this, "No trainer is logged in!", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        Trainer trainer = (Trainer) currentUser;
        adapter = new TrainerActionsProxy();

        try {
            addPanels(trainer);
            addSidebar();
            setVisible(true);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error initializing dashboard: " + e.getMessage(), e);
            JOptionPane.showMessageDialog(this, "Failed to load dashboard: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    private JPanel decoratePanel(JPanel panel) {
        try {
            BasicPanel basicPanel = new BasicPanel(panel);
            basicPanel.applyDecorations();

            ShadowDecorator shadowDecorator = new ShadowDecorator(basicPanel);
            shadowDecorator.applyDecorations();

            BorderDecorator borderDecorator = new BorderDecorator(shadowDecorator);
            borderDecorator.applyDecorations();

            return borderDecorator.getPanel();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error decorating panel: " + e.getMessage(), e);
            return panel;
        }
    }

    private void addPanels(Trainer trainer) {
        try {
            User userTrainer = trainer;
            panelMap.put("Courses", decoratePanel(new ManageCoursesPanel(userTrainer)));
            panelMap.put("Sessions", decoratePanel(new SessionsPanel(new MockSessionRepository(), new TrainerSessionFilterStrategy())));
            quizzesPanelRef = new QuizzesPanel();
            panelMap.put("Quizzes", decoratePanel(quizzesPanelRef));

            for (Map.Entry<String, JPanel> entry : panelMap.entrySet()) {
                contentPanel.add(entry.getValue(), entry.getKey());
            }

            JPanel homePanel = new JPanel(new BorderLayout());
            JLabel welcome = new JLabel("Welcome to Trainer Dashboard!", JLabel.CENTER);
            welcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
            homePanel.add(welcome, BorderLayout.CENTER);
            contentPanel.add(decoratePanel(homePanel), "Home");

            add(contentPanel, BorderLayout.CENTER);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding panels: " + e.getMessage(), e);
            throw e;
        }
    }

    private void addSidebarButton(String title, Command command) {
        if (command == null) {
            logger.warning("Command for button '" + title + "' is null, skipping");
            return;
        }

        JButton button = createSidebarButton(title);
        button.addActionListener(e -> {
            try {
                command.execute();
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Error executing command '" + title + "': " + ex.getMessage(), ex);
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Command Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        sidebar.add(button);
    }

    private void addSidebarButton(String title, Runnable action) {
        if (action == null) {
            logger.warning("Action for button '" + title + "' is null, skipping");
            return;
        }

        JButton button = createSidebarButton(title);
        button.addActionListener(e -> {
            try {
                action.run();
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Error executing action '" + title + "': " + ex.getMessage(), ex);
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Action Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        sidebar.add(button);
    }

    private void addSidebar() {
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(44, 62, 80));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        JPanel homePanel = contentPanel.getComponentCount() > 0 ?
            (JPanel) contentPanel.getComponent(0) : new JPanel(new BorderLayout());

        if (contentPanel.getComponentCount() == 0) {
            logger.warning("contentPanel is empty, using fallback home panel");
            JLabel fallbackLabel = new JLabel("No panels loaded", JLabel.CENTER);
            fallbackLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            homePanel.add(fallbackLabel, BorderLayout.CENTER);
        }

        // Add buttons with proper Command pattern implementation
        addSidebarButton("Home", (Command) new HomeCommand(contentPanel, homePanel));

        // Course management buttons
        if (adapter != null) {
            addSidebarButton("Create Course", (Runnable) adapter::createCourse);
            addSidebarButton("Edit Course", (Runnable) adapter::editCourse);
            addSidebarButton("Delete Course", (Runnable) adapter::deleteCourse);

            // Session management buttons
            addSidebarButton("Create Session", (Runnable) adapter::createSession);
            addSidebarButton("Edit Session", (Runnable) adapter::editSession);
            addSidebarButton("Delete Session", (Runnable) adapter::deleteSession);

            // Quiz management buttons
            addSidebarButton("Create Quiz", (Runnable) adapter::createQuiz);
            addSidebarButton("Edit Quiz", (Runnable) adapter::editQuiz);
        }

        // Add delete quiz command
        if (quizzesPanelRef != null) {
            addSidebarButton("Delete Quiz", (Command) new DeleteQuizCommand(quizzesPanelRef.getQuizTable()));
        }

        // Logout button
        addSidebarButton("Logout", (Runnable) () -> {
            try {
                DBConnection.getInstance().closeConnection();
                dispose();
                System.exit(0);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error during logout: " + e.getMessage(), e);
            }
        });

        JScrollPane scrollPane = new JScrollPane(sidebar);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.WEST);
    }

    private JButton createSidebarButton(String title) {
        JButton button = new JButton(title);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 40));
        button.setFocusPainted(false);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return button;
    }

    public static void main(String[] args) {
        // Set up a sample trainer for testing using Builder pattern
        Trainer trainer = new Trainer.Builder()
                .setId(1)
                .setFirstname("John")
                .setLastname("Doe")
                .setMiddlename("")
                .setEmail("john@example.com")
                .setUsername("johndoe")
                .setPassword("password")
                .setPhoneNumber("")
                .setSpecialization("Java Development")
                .build();

        // Set the trainer as current user
        SessionManager.getInstance().setCurrentUser(trainer);

        // Launch the dashboard
        SwingUtilities.invokeLater(() -> {
            try {
                TrainerDashboard dashboard = new TrainerDashboard();
                dashboard.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TrainerDashboard.class.getName())
                      .log(Level.SEVERE, "Error launching dashboard", e);
            }
        });
    }
}