package view;

import model.Course;
import model.Trainer;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class CourseDetails extends JFrame {

    public CourseDetails(Course course) throws SQLException {
        // Window setup
        setTitle("Course Details: " + course.getName());
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        // Title
        JLabel titleLabel = new JLabel(course.getName(), JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 26));
        titleLabel.setForeground(new Color(52, 152, 219));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Detail Panel
        JPanel detailsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        detailsPanel.setBackground(new Color(245, 245, 245));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));

        // Level
        JLabel levelLabel = new JLabel("Level: " + course.getLevel());
        levelLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        levelLabel.setForeground(new Color(52, 152, 219));
        detailsPanel.add(levelLabel);

        // Trainer
        Trainer trainer = (Trainer) new dao.UserDaoImpl().getUserById(course.getTrainerId());
        JLabel trainerLabel = new JLabel("Trainer: " + trainer.getFirstName() + " " + trainer.getLastName());
        trainerLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        trainerLabel.setForeground(new Color(52, 152, 219));
        detailsPanel.add(trainerLabel);

        // Cost
        JLabel costLabel = new JLabel("Cost: $" + course.getCost());
        costLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        costLabel.setForeground(new Color(39, 174, 96));
        detailsPanel.add(costLabel);

        // Description
        JLabel descriptionLabel = new JLabel(
                "<html><div style='width: 350px;'>Description: " + course.getDescription() + "</div></html>");
        descriptionLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        descriptionLabel.setForeground(Color.GRAY);
        detailsPanel.add(descriptionLabel);

        // Dates
        JLabel datesLabel = new JLabel(
                "<html><div style='width: 350px;'>Dates: " + course.getStartDate() + " to " + course.getEndDate() + "</div></html>");
        datesLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        datesLabel.setForeground(Color.GRAY);
        detailsPanel.add(datesLabel);

        add(detailsPanel, BorderLayout.CENTER);

        // Enroll button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton enrollButton = new JButton("Enroll Now");
        enrollButton.setFont(new Font("Roboto", Font.BOLD, 16));
        enrollButton.setBackground(new Color(52, 152, 219));
        enrollButton.setForeground(Color.WHITE);
        enrollButton.setFocusPainted(false);
        enrollButton.setPreferredSize(new Dimension(200, 40));

        enrollButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enrollButton.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                enrollButton.setBackground(new Color(52, 152, 219));
            }
        });

        enrollButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(CourseDetails.this,
                    "You have enrolled in: " + course.getName());
            dispose();
        });

        buttonPanel.add(enrollButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
