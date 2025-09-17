package adapter;

import model.Course;
import dao.CourseDAOImpl;
import observer.CourseObserver;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class DefaultCourseEnrollmentAdapter implements CourseEnrollmentAdapter {
    private final CourseDAOImpl courseDAO;
    private final List<Object> observers;
    private final int auditorId;

    public DefaultCourseEnrollmentAdapter(int auditorId) {
        this.auditorId = auditorId;
        this.courseDAO = new dao.CourseDAOImpl(auditorId); // Pass auditorId to DAO
        this.observers = new ArrayList<>();
    }

    @Override
    public Map<String, List<Course>> getCoursesByCategory() {
        List<Course> allCourses = courseDAO.getAllCourses(); // Using correct method name
        Map<String, List<Course>> coursesByCategory = new HashMap<>();

        for (Course course : allCourses) {
            String category = course.getCategory();
            if (!coursesByCategory.containsKey(category)) {
                coursesByCategory.put(category, new ArrayList<>());
            }
            coursesByCategory.get(category).add(course);
        }
        return coursesByCategory;
    }

    @Override
    public JComponent renderCourses(Map<String, List<Course>> coursesByCategory) {
        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Map.Entry<String, List<Course>> entry : coursesByCategory.entrySet()) {
            JPanel categoryPanel = new JPanel(new BorderLayout());
            categoryPanel.setBorder(BorderFactory.createTitledBorder(entry.getKey()));

            JPanel coursesPanel = new JPanel(new GridLayout(0, 3, 5, 5));
            for (Course course : entry.getValue()) {
                JPanel coursePanel = createCoursePanel(course);
                coursesPanel.add(coursePanel);
            }

            categoryPanel.add(new JScrollPane(coursesPanel), BorderLayout.CENTER);
            mainPanel.add(categoryPanel);
        }

        return new JScrollPane(mainPanel);
    }

    private JPanel createCoursePanel(Course course) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEtchedBorder());

        JLabel titleLabel = new JLabel(course.getTitle());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(titleLabel, BorderLayout.NORTH);

        JLabel instructorLabel = new JLabel("Trainer ID: " + course.getTrainerId()); // Show trainer ID
        panel.add(instructorLabel, BorderLayout.CENTER);

        // Add more components to display course details as needed

        return panel;
    }

    @Override
    public void registerObserver(Object observer) {
        addObserver(observer);
    }

    private void addObserver(Object observer) {
        observers.add(observer);
    }

    // Notify all registered observers about a change
    private void notifyObservers() {
        for (Object observer : observers) {
            if (observer instanceof CourseObserver) {
                ((CourseObserver) observer).update(getCoursesByCategory()); // Pass required argument
            }
        }
    }

    // Call this method whenever the course data changes
    public void onCourseDataChanged() {
        notifyObservers();
    }
}
