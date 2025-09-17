package view;

import composite.CompositeComponent;
import composite.LeafComponent;
import composite.UIComponent;
import decorator.BorderDecorator;
import decorator.ShadowDecorator;
import decorator.BasicPanel;
import facade.DashboardFacade;
import facade.PaymentFacade;
import repository.CourseRepository;
import repository.MockCourseRepository;
import repository.QuizRepository;
import repository.MockQuizRepository;
import repository.PaymentRepository;
import repository.MockPaymentRepository;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private CardLayout cardLayout;
    private CompositeComponent mainContentComposite;
    private DashboardFacade facade;

    public DashboardPanel() {
        this(new MockCourseRepository(), new MockQuizRepository(), new MockPaymentRepository());
    }

    public DashboardPanel(CourseRepository courseRepository, QuizRepository quizRepository, PaymentRepository paymentRepository) {
        setLayout(new BorderLayout());
        this.facade = new DashboardFacade(courseRepository, quizRepository, paymentRepository);

        // Sidebar for navigation
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(0, 1, 5, 5));
        sidebar.setBackground(new Color(44, 62, 80));
        sidebar.setPreferredSize(new Dimension(200, 0));
        CompositeComponent sidebarComposite = new CompositeComponent(sidebar);

        // Create sidebar buttons as leaf components
        UIComponent coursesBtn = new LeafComponent(createSidebarButton("Courses"));
        UIComponent addCourseBtn = new LeafComponent(createSidebarButton("Add Course"));
        UIComponent quizBtn = new LeafComponent(createSidebarButton("Quizzes"));
        UIComponent paymentBtn = new LeafComponent(createSidebarButton("Payments"));

        // Add buttons to sidebar composite
        sidebarComposite.add(coursesBtn);
        sidebarComposite.add(addCourseBtn);
        sidebarComposite.add(quizBtn);
        sidebarComposite.add(paymentBtn);

        // Main content area
        cardLayout = new CardLayout();
        JPanel mainContent = new JPanel(cardLayout);
        mainContentComposite = new CompositeComponent(mainContent);

        // Get actual auditor ID dynamically in future
        int auditorId = 0;

        // Panels with Decorators applied
        JPanel enrollCoursesPanelCore = new EnrollCoursesPanel2(auditorId);
        JPanel enrollCoursesPanel = (JPanel) new ShadowDecorator(new BorderDecorator(new BasicPanel(enrollCoursesPanelCore))).getPanel();
        UIComponent enrollCoursesComponent = new LeafComponent(enrollCoursesPanel);

        JPanel addCoursePanelCore = new CoursePanel(facade);
        JPanel addCoursePanel = (JPanel) new ShadowDecorator(new BorderDecorator(new BasicPanel(addCoursePanelCore))).getPanel();
        UIComponent addCourseComponent = new LeafComponent(addCoursePanel);

        JPanel quizzesPanelCore = new JPanel();
        quizzesPanelCore.add(new JLabel("Quizzes Panel (To be implemented)"));
        JPanel quizzesPanel = (JPanel) new ShadowDecorator(new BorderDecorator(new BasicPanel(quizzesPanelCore))).getPanel();
        UIComponent quizzesComponent = new LeafComponent(quizzesPanel);

        JPanel paymentsPanelCore = new PaymentScreen(auditorId, new PaymentFacade(paymentRepository));
        JPanel paymentsPanel = (JPanel) new ShadowDecorator(new BorderDecorator(new BasicPanel(paymentsPanelCore))).getPanel();
        UIComponent paymentsComponent = new LeafComponent(paymentsPanel);

        // Add decorated panels to main content composite
        mainContentComposite.add(enrollCoursesComponent);
        mainContentComposite.add(addCourseComponent);
        mainContentComposite.add(quizzesComponent);
        mainContentComposite.add(paymentsComponent);

        // Add panels to card layout (required for CardLayout to function)
        mainContent.add(enrollCoursesPanel, "COURSES");
        mainContent.add(addCoursePanel, "ADD_COURSE");
        mainContent.add(quizzesPanel, "QUIZZES");
        mainContent.add(paymentsPanel, "PAYMENTS");

        // Button click logic
        ((JButton) coursesBtn.getComponent()).addActionListener(e -> cardLayout.show(mainContent, "COURSES"));
        ((JButton) addCourseBtn.getComponent()).addActionListener(e -> cardLayout.show(mainContent, "ADD_COURSE"));
        ((JButton) quizBtn.getComponent()).addActionListener(e -> cardLayout.show(mainContent, "QUIZZES"));
        ((JButton) paymentBtn.getComponent()).addActionListener(e -> cardLayout.show(mainContent, "PAYMENTS"));

        // Layout structure
        add(sidebarComposite.getComponent(), BorderLayout.WEST);
        add(mainContentComposite.getComponent(), BorderLayout.CENTER);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(52, 73, 94));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 73, 94));
            }
        });

        return button;
    }
}