package proxy;

import model.Course;
import model.User;
import observer.Observer;
import session.SessionManager;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseEnrollmentProxy implements CourseEnrollmentSubject {
    private static final Logger logger = Logger.getLogger(CourseEnrollmentProxy.class.getName());
    private final CourseEnrollmentSubject realReceiver;
    private final int auditorId;

    public CourseEnrollmentProxy(int auditorId) {
        if (auditorId <= 0) {
            throw new IllegalArgumentException("Invalid auditor ID: " + auditorId);
        }
        this.auditorId = auditorId;
        try {
            this.realReceiver = new CourseEnrollmentRealReceiver(auditorId);
            logger.info("✅ Initialized real receiver for auditor ID: " + auditorId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize real receiver for auditor ID: " + auditorId, e);
            throw new IllegalStateException("Could not initialize course enrollment system", e);
        }
    }

    @Override
    public Map<String, List<Course>> getCoursesByCategory() {
        logger.info("🎯 Fetching courses by category for auditor ID: " + auditorId);
        if (!hasAccess()) {
            logger.warning("❌ Access denied: User not authenticated or not an auditor");
            return new HashMap<>();
        }
        try {
            Map<String, List<Course>> courses = realReceiver.getCoursesByCategory();
            logger.info("✅ Successfully fetched courses by category for auditor ID: " + auditorId);
            return courses;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching courses by category for auditor ID: " + auditorId, e);
            return new HashMap<>();
        }
    }

    @Override
    public JPanel renderCourses(Map<String, List<Course>> courses) {
        logger.info("🎨 Rendering courses for auditor ID: " + auditorId);
        if (!hasAccess()) {
            logger.warning("❌ Access denied: User not authenticated or not an auditor");
            JPanel errorPanel = new JPanel();
            errorPanel.add(new JLabel("Access denied. Please log in as an auditor."));
            return errorPanel;
        }
        try {
            JPanel renderedPanel = realReceiver.renderCourses(courses);
            logger.info("✅ Successfully rendered courses for auditor ID: " + auditorId);
            return renderedPanel;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error rendering courses for auditor ID: " + auditorId, e);
            JPanel errorPanel = new JPanel();
            errorPanel.add(new JLabel("Error rendering courses. Please try again later."));
            return errorPanel;
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer == null) {
            logger.warning("❌ Attempted to register null observer for auditor ID: " + auditorId);
            return;
        }
        if (!hasAccess()) {
            logger.warning("❌ Access denied: Cannot register observer for auditor ID: " + auditorId);
            return;
        }
        logger.info("🔔 Registering observer for auditor ID: " + auditorId);
        try {
            realReceiver.registerObserver(observer);
            logger.info("✅ Observer registered for auditor ID: " + auditorId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error registering observer for auditor ID: " + auditorId, e);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer == null) {
            logger.warning("❌ Attempted to remove null observer for auditor ID: " + auditorId);
            return;
        }
        if (!hasAccess()) {
            logger.warning("❌ Access denied: Cannot remove observer for auditor ID: " + auditorId);
            return;
        }
        logger.info("🔔 Removing observer for auditor ID: " + auditorId);
        try {
            realReceiver.removeObserver(observer);
            logger.info("✅ Observer removed for auditor ID: " + auditorId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error removing observer for auditor ID: " + auditorId, e);
        }
    }

    @Override
    public void notifyObservers(Object data) {
        if (!hasAccess()) {
            logger.warning("❌ Access denied: Cannot notify observers for auditor ID: " + auditorId);
            return;
        }
        logger.info("🔔 Notifying observers for auditor ID: " + auditorId);
        try {
            realReceiver.notifyObservers(data);
            logger.info("✅ Observers notified for auditor ID: " + auditorId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error notifying observers for auditor ID: " + auditorId, e);
        }
    }

    private boolean hasAccess() {
        try {
            User currentUser = SessionManager.getInstance().getCurrentUser();
            boolean hasAccess = currentUser != null &&
                    "Auditor".equalsIgnoreCase(currentUser.getUsertype()) &&
                    currentUser.getId() == auditorId;
            if (!hasAccess) {
                logger.warning("❌ Access check failed: User ID=" + (currentUser != null ? currentUser.getId() : "null") +
                        ", UserType=" + (currentUser != null ? currentUser.getUsertype() : "null") +
                        ", Expected Auditor ID=" + auditorId);
            } else {
                logger.info("✅ Access granted for auditor ID: " + auditorId);
            }
            return hasAccess;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error checking access for auditor ID: " + auditorId, e);
            return false;
        }
    }
}