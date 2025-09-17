package view;

import facade.DashboardFacade;
import model.Course;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class CoursePanel extends JPanel {
    private JPanel contentPanel;
    private DashboardFacade facade;

    public CoursePanel(DashboardFacade facade) {
        this.facade = facade;
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Courses by Category", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Initial data load
        refreshCourseList();
    }

    private void refreshCourseList() {
        Map<String, List<Course>> coursesByCategory = facade.getCoursesByCategory();
        contentPanel.removeAll();

        for (Map.Entry<String, List<Course>> entry : coursesByCategory.entrySet()) {
            String category = entry.getKey();
            List<Course> courses = entry.getValue();

            JLabel categoryLabel = new JLabel("📚 " + category);
            categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
            contentPanel.add(categoryLabel);

            for (Course course : courses) {
                JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel courseLabel = new JLabel(" - " + course.getName() + " | Level: " + course.getLevel());
                courseLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                courseLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        onCourseSelected(course);
                    }
                });
                coursePanel.add(courseLabel);
                contentPanel.add(coursePanel);
            }

            contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void onCourseSelected(Course course) {
        JOptionPane.showMessageDialog(this,
                "Selected Course: " + course.getTitle() + "\n" +
                        "Description: " + course.getDescription(),
                "Course Details",
                JOptionPane.INFORMATION_MESSAGE);
    }
}