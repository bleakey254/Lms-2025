package component;

import command.EnrollCommand;
import command.CommandInvoker;
import model.Course;
import facade.EnrollCoursesFacade;

import javax.swing.*;
import java.awt.*;

public class CourseCard extends JPanel {

    private final Course course;
    private final int auditorId;
    private final EnrollCoursesFacade facade;

    public CourseCard(Course course, int auditorId, EnrollCoursesFacade facade) {
        this.course = course;
        this.auditorId = auditorId;
        this.facade = facade;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(700, 100));
        setMaximumSize(new Dimension(800, 100));

        JLabel nameLabel = new JLabel(course.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel descriptionLabel = new JLabel("<html><body style='width: 400px'>" + course.getDescription() + "</body></html>");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JButton enrollButton = new JButton("Enroll");
        enrollButton.setFocusPainted(false);
        enrollButton.setBackground(new Color(52, 152, 219));
        enrollButton.setForeground(Color.WHITE);
        enrollButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Command pattern: create and execute enrollment command
        enrollButton.addActionListener(e -> {
            EnrollCommand enrollCommand = new EnrollCommand(facade, course, auditorId);
            new CommandInvoker().executeCommand(enrollCommand);
        });

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(false);
        infoPanel.add(nameLabel, BorderLayout.NORTH);
        infoPanel.add(descriptionLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(enrollButton);

        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
    }
}
