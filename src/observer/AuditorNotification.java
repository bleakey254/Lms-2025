package observer;

import model.Course;
import java.util.function.Consumer;

public class AuditorNotification implements Observer {
    private final String auditorName;
    private final String auditorEmail;
    private final Consumer<String> uiCallback;

    public AuditorNotification(String name, String email, Consumer<String> uiCallback) {
        this.auditorName = name;
        this.auditorEmail = email;
        this.uiCallback = uiCallback;
    }

    @Override
    public void update(Object eventData) {
        String message;

        if (eventData instanceof Course course) {
            message = "📘 Course Updated: " + course.getName() + " (Level: " + course.getLevel() + ")";
        } else if (eventData instanceof String strMessage) {
            message = strMessage;
        } else {
            message = "⚠️ Unsupported event for auditor: " + auditorName;
        }

        // Print to console
        System.out.println("📢 Auditor Notification");
        System.out.println("👤 Name : " + auditorName);
        System.out.println("📧 Email: " + auditorEmail);
        System.out.println("🔔 " + message);

        // Send to GUI
        if (uiCallback != null) {
            uiCallback.accept("🔔 " + message);
        }
    }

    public String getEmail() {
        return auditorEmail;
    }

    public String getName() {
        return auditorName;
    }
}
