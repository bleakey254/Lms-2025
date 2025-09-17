// File: factory/DashboardFactoryProducer.java
package factory;

import model.Auditor;
import model.Trainer;
import model.User;

public class DashboardFactoryProducer {
    public static DashboardFactory getFactory(User user) {
        if (user instanceof Trainer) {
            return new TrainerDashboardFactory();
        } else if (user instanceof Auditor) {
            return new AuditorDashboardFactory();
        }
        throw new IllegalArgumentException("Unsupported user role: " + user.getClass());
    }
}
