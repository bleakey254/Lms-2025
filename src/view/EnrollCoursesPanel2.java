package view;

import decorator.ShadowDecorator;
import decorator.BorderDecorator;
import decorator.BasicPanel;
import decorator.PanelComponent;
import model.Course;
import observer.Observer;
import proxy.CourseEnrollmentProxy;
import proxy.CourseEnrollmentSubject;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class EnrollCoursesPanel2 extends JPanel implements Observer {

    private final JPanel coursesPanel;
    private final JScrollPane scrollPane;
    private final CourseEnrollmentSubject proxy;

    public EnrollCoursesPanel2(int auditorId) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        coursesPanel = new JPanel();
        coursesPanel.setLayout(new BoxLayout(coursesPanel, BoxLayout.Y_AXIS));
        coursesPanel.setBackground(new Color(245, 245, 245));

        PanelComponent basePanel = new BasicPanel(coursesPanel);
        PanelComponent decoratedPanel = new ShadowDecorator(new BorderDecorator(basePanel));

        scrollPane = new JScrollPane(decoratedPanel.getPanel());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.CENTER);

        // Use proxy
        proxy = new CourseEnrollmentProxy(auditorId);
        proxy.registerObserver(this);

        try {
            update(proxy.getCoursesByCategory());
        } catch (Exception e) {
            System.err.println("❌ Failed to fetch courses: " + e.getMessage());
        }
    }

    @Override
    public void update(Object eventData) {
        if (eventData instanceof Map) {
            Map<String, List<Course>> coursesByCategory = (Map<String, List<Course>>) eventData;
            coursesPanel.removeAll();
            coursesPanel.add(proxy.renderCourses(coursesByCategory));
            coursesPanel.revalidate();
            coursesPanel.repaint();
        }
    }
}
