package facade;

import adapter.CourseAdapter;
import dao.EnrollmentDao;
import model.Course;
import observer.Observer;
import observer.Subject;
import view.CourseCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnrollCoursesFacade implements Subject {

    private final CourseAdapter courseAdapter;
    private final EnrollmentDao enrollmentDAO;
    private final List<Observer> observers = new ArrayList<>();
    private final int auditorId;

    public EnrollCoursesFacade(int auditorId) {
        this.auditorId = auditorId;
        this.courseAdapter = new adapter.CourseDAOAdapter(auditorId);
        this.enrollmentDAO = new dao.EnrollmentDaoImpl(); // Use the concrete implementation
    }

    public Map<String, List<Course>> getCoursesByCategory() {
        return courseAdapter.getCoursesByCategory();
    }

    public JPanel renderCourses(Map<String, List<Course>> coursesByCategory) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);

        for (Map.Entry<String, List<Course>> entry : coursesByCategory.entrySet()) {
            String category = entry.getKey();
            List<Course> courses = entry.getValue();

            JLabel categoryLabel = new JLabel(category);
            categoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            categoryLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
            container.add(categoryLabel);

            JPanel courseListPanel = new JPanel();
            courseListPanel.setLayout(new GridLayout(0, 1, 10, 10));
            courseListPanel.setBackground(new Color(245, 245, 245));

            for (Course course : courses) {
                CourseCard card = new CourseCard(course, this, auditorId);
                courseListPanel.add(card);
            }

            container.add(courseListPanel);
        }

        return container;
    }

    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Object eventData) {
        for (Observer observer : observers) {
            observer.update(eventData);
        }
    }

    public void notifyObservers() {
        notifyObservers(getCoursesByCategory());
    }

    public void registerObserver(Observer observer) {
        addObserver(observer);
    }

    // ✅ Enrollment logic hooked to DB and UI refresh
    public boolean enrollInCourse(int courseId) {
        boolean success = enrollmentDAO.enrollUser(auditorId, courseId);
        if (success) {
            notifyObservers(); // Refresh views
        }
        return success;
    }
}
