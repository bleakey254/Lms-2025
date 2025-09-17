// File: factory/TrainerDashboardFactory.java
package factory;

import model.Trainer;
import model.User;
import view.TrainerDashboard;

import javax.swing.*;
import factory.DashboardFactory;

public class TrainerDashboardFactory implements DashboardFactory {
    @Override
    public JFrame createDashboard(User user) {
        return new TrainerDashboard();
    }
}
