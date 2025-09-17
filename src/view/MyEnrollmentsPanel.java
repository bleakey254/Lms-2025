package view;

import decorator.ShadowDecorator;
import decorator.BorderDecorator;
import decorator.BasicPanel;
import decorator.PanelComponent;
import model.Enrollment;
import proxy.EnrollmentAccessProxy;
import strategy.EnrollmentDisplayStrategy;
import strategy.TableEnrollmentDisplayStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyEnrollmentsPanel extends JPanel {

    private EnrollmentDisplayStrategy displayStrategy;
    private final int auditorId;

    public MyEnrollmentsPanel(int auditorId) {
        this.auditorId = auditorId;
        this.displayStrategy = new TableEnrollmentDisplayStrategy();

        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 241));

        loadAndDisplayEnrollments();
    }

    private void loadAndDisplayEnrollments() {
        removeAll();

        // Using the proxy to get the panel
        EnrollmentAccessProxy proxy = new EnrollmentAccessProxy(auditorId);
        JPanel enrollmentPanel = proxy.getEnrollmentsPanel(auditorId);

        // Apply decorators
        PanelComponent basePanel = new BasicPanel(enrollmentPanel);
        PanelComponent decoratedPanel = new ShadowDecorator(new BorderDecorator(basePanel));

        add(decoratedPanel.getPanel(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setDisplayStrategy(EnrollmentDisplayStrategy strategy) {
        this.displayStrategy = strategy;
        loadAndDisplayEnrollments();
    }
}
