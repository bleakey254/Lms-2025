// File: factory/AuditorDashboardFactory.java
package factory;

import model.Auditor;
import model.User;
import view.AuditorDashboard;

import javax.swing.*;
import factory.DashboardFactory;

public class AuditorDashboardFactory implements DashboardFactory {
    @Override
    public JFrame createDashboard(User user) {
        return new AuditorDashboard();
    }
}
