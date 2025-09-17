package view;

import decorator.BorderDecorator;
import decorator.BasicPanel;
import decorator.PanelComponent;
import decorator.ShadowDecorator;
import facade.EnrollCoursesFacade;
import model.Course;

import javax.swing.*;
import java.awt.*;

public class CourseCard extends JPanel {
    private final Course course;

    public CourseCard(Course course, EnrollCoursesFacade facade, int auditorId) {
        this.course = course;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Build base panel with course info and button
        JPanel basePanel = new JPanel(new BorderLayout(10, 10));
        basePanel.setBackground(Color.WHITE);
        basePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel titleLabel = new JLabel(course.getTitle());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton enrollButton = new JButton("Enroll");
        enrollButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        enrollButton.setFocusPainted(false);
        enrollButton.addActionListener(e -> {
            boolean success = facade.enrollInCourse(course.getId());
            if (success) {
                JOptionPane.showMessageDialog(this,
                        "✅ Successfully enrolled in: " + course.getTitle(),
                        "Enrollment Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "❌ Failed to enroll in: " + course.getTitle(),
                        "Enrollment Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        basePanel.add(titleLabel, BorderLayout.CENTER);
        basePanel.add(enrollButton, BorderLayout.EAST);

        // Apply decorators
        PanelComponent decorated = new ShadowDecorator(new BorderDecorator(new BasicPanel(basePanel)));

        add(decorated.getPanel(), BorderLayout.CENTER);
    }

    public Course getCourse() {
        return course;
    }
}
