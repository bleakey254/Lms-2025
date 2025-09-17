package factory;

import model.User;

import javax.swing.*;

public interface DashboardFactory {
    JFrame createDashboard(User user);
}
