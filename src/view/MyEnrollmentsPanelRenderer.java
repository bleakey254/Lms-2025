package view;

import adapter.CourseEnrollmentAdapter;
import factory.EnrollmentAdapterFactory;
import model.Course;
import proxy.EnrollmentReceiver;
import decorator.BasicPanel;
import decorator.BorderDecorator;
import decorator.PanelComponent;
import decorator.ShadowDecorator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class MyEnrollmentsPanelRenderer implements EnrollmentReceiver {
    private JTable enrollmentTable;
    private DefaultTableModel tableModel;
    private final CourseEnrollmentAdapter enrollmentAdapter;
    private final int auditorId;

    public MyEnrollmentsPanelRenderer(int auditorId) {
        this.auditorId = auditorId;
        this.enrollmentAdapter = EnrollmentAdapterFactory.createAdapter(auditorId);
        initEnrollmentTable();
    }

    @Override
    public JPanel getEnrollmentsPanel(int userId) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("📚 My Enrollments", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(title, BorderLayout.NORTH);

        loadEnrollments(userId);

        JScrollPane scrollPane = new JScrollPane(enrollmentTable);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Apply decorators
        PanelComponent base = new BasicPanel(contentPanel);
        PanelComponent decorated = new ShadowDecorator(new BorderDecorator(base));
        mainPanel.add(decorated.getPanel(), BorderLayout.CENTER);

        return mainPanel;
    }

    private void initEnrollmentTable() {
        String[] columnNames = {"Course Title", "Instructor", "Enrolled On"};
        tableModel = new DefaultTableModel(columnNames, 0);
        enrollmentTable = new JTable(tableModel);
        enrollmentTable.setRowHeight(25);
        enrollmentTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        enrollmentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    private void loadEnrollments(int userId) {
        try {
            java.util.List<Course> courses = new ArrayList<>();
            Map<String, java.util.List<Course>> coursesByCategory = enrollmentAdapter.getCoursesByCategory();

            // Filter courses for the specific user if needed
            java.util.List<Course> userCourses = new ArrayList<>();
            for (java.util.List<Course> categoryCourses : coursesByCategory.values()) {
                for (Course course : categoryCourses) {
                    if (isUserEnrolled(userId, course.getId())) {
                        userCourses.add(course);
                    }
                }
            }

            tableModel.setRowCount(0);
            for (Course course : userCourses) {
                String trainerName = getTrainerNameById(course.getTrainerId());
                tableModel.addRow(new Object[]{
                    course.getName(),
                    trainerName,
                    course.getStartDate()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                "Error loading enrollments: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isUserEnrolled(int userId, int courseId) {
        // TODO: Implement enrollment check
        return true; // Placeholder implementation
    }

    private String getTrainerNameById(int trainerId) {
        // TODO: Implement trainer lookup service
        return "Trainer #" + trainerId;
    }
}
