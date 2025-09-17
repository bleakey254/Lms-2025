package strategy;

import model.Course;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EnrollCourseDisplayStrategy implements CourseDisplayStrategy {
    @Override
    public JPanel generateCourseView(Map<String, List<Course>> coursesByCategory) {
        JPanel coursesPanel = new JPanel();
        coursesPanel.setLayout(new BoxLayout(coursesPanel, BoxLayout.Y_AXIS));
        coursesPanel.setBackground(new Color(245, 245, 245));

        if (coursesByCategory == null || coursesByCategory.isEmpty()) {
            JLabel noCoursesLabel = new JLabel("No courses available at the moment.");
            noCoursesLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            noCoursesLabel.setForeground(Color.GRAY);
            noCoursesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            coursesPanel.add(noCoursesLabel);
        } else {
            for (Map.Entry<String, List<Course>> entry : coursesByCategory.entrySet()) {
                String category = entry.getKey();
                List<Course> courses = entry.getValue();

                JPanel categoryPanel = new JPanel();
                categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
                categoryPanel.setBorder(BorderFactory.createTitledBorder(category));
                categoryPanel.setBackground(new Color(250, 250, 250));

                for (Course course : courses) {
                    JPanel courseCard = createCourseCard(course);
                    categoryPanel.add(Box.createVerticalStrut(10));
                    categoryPanel.add(courseCard);
                }

                coursesPanel.add(Box.createVerticalStrut(20));
                coursesPanel.add(categoryPanel);
            }
        }

        return coursesPanel;
    }

    private JPanel createCourseCard(Course course) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        JLabel titleLabel = new JLabel(course.getName());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 152, 219));

        JLabel levelLabel = new JLabel("Level: " + course.getLevel());
        JLabel feeLabel = new JLabel("Fee: $" + course.getCost());

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(levelLabel);
        infoPanel.add(feeLabel);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.add(titleLabel, BorderLayout.NORTH);
        textPanel.add(infoPanel, BorderLayout.CENTER);

        JButton enrollButton = new JButton("Enroll");
        enrollButton.addActionListener(e -> {
            try {
                new view.CourseDetails(course).setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        card.add(textPanel, BorderLayout.CENTER);
        card.add(enrollButton, BorderLayout.EAST);

        return card;
    }
}
