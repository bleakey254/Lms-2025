package proxy;

import model.Course;
import observer.Observer;
import dao.CourseDAO;
import dao.CourseDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseEnrollmentRealReceiver implements CourseEnrollmentSubject {
    private static final Logger logger = Logger.getLogger(CourseEnrollmentRealReceiver.class.getName());
    private final List<Observer> observers;
    private final int auditorId;
    private final CourseDAO courseDAO;

    public CourseEnrollmentRealReceiver(int auditorId) {
        this.auditorId = auditorId;
        this.observers = new ArrayList<>();
        this.courseDAO = new CourseDAOImpl();
    }

    @Override
    public Map<String, List<Course>> getCoursesByCategory() {
        try {
            List<Course> allCourses = courseDAO.getAllCourses();
            Map<String, List<Course>> coursesByCategory = new HashMap<>();

            for (Course course : allCourses) {
                coursesByCategory
                    .computeIfAbsent(course.getCategory(), k -> new ArrayList<>())
                    .add(course);
            }

            return coursesByCategory;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching courses for auditor ID: " + auditorId, e);
            return new HashMap<>();
        }
    }

    @Override
    public JPanel renderCourses(Map<String, List<Course>> courses) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        for (Map.Entry<String, List<Course>> entry : courses.entrySet()) {
            String category = entry.getKey();
            List<Course> categoryCourses = entry.getValue();

            // Category header
            JPanel categoryPanel = new JPanel();
            categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
            JLabel categoryLabel = new JLabel(category);
            categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
            categoryPanel.add(categoryLabel);

            // Courses in this category
            JPanel coursesPanel = new JPanel();
            coursesPanel.setLayout(new GridLayout(0, 2, 10, 10));
            for (Course course : categoryCourses) {
                JPanel courseCard = createCourseCard(course);
                coursesPanel.add(courseCard);
            }

            categoryPanel.add(coursesPanel);
            mainPanel.add(categoryPanel);
            mainPanel.add(Box.createVerticalStrut(20));
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(scrollPane, BorderLayout.CENTER);
        return containerPanel;
    }

    private JPanel createCourseCard(Course course) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(course.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel trainerLabel = new JLabel("Trainer ID: " + course.getTrainerId());
        JLabel levelLabel = new JLabel("Level: " + course.getLevel());
        JLabel costLabel = new JLabel(String.format("Cost: $%.2f", course.getCost()));

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(trainerLabel);
        card.add(levelLabel);
        card.add(costLabel);

        return card;
    }

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            logger.info("Observer registered for auditor ID: " + auditorId);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        logger.info("Observer removed for auditor ID: " + auditorId);
    }

    @Override
    public void notifyObservers(Object data) {
        for (Observer observer : observers) {
            try {
                observer.update(data);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error notifying observer for auditor ID: " + auditorId, e);
            }
        }
    }
}
