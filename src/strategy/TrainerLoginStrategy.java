package strategy;

import dao.UserDao;
import model.Trainer;
import model.User;
import view.LoginPageTrainer;

import javax.swing.*;

public class TrainerLoginStrategy implements RoleLoginStrategy, LoginStrategy {

    @Override
    public void login(JFrame parent) {
        SwingUtilities.invokeLater(() -> new LoginPageTrainer(parent, this));
    }

    @Override
    public int login(String email, String password) {
        try {
            dao.UserDao userDao = new dao.UserDaoImpl();
            int id = userDao.getUserIdByCredentials(email, password);
            if (id <= 0) return 0;

            User user = userDao.getUserById(id);
            // If you have a getRole() method, use this instead:
            // if ("trainer".equalsIgnoreCase(user.getRole())) {
            if (user instanceof Trainer) {
                System.out.println("✅ Logged in as Trainer ID: " + id);
                return id;
            } else {
                System.err.println("❌ Not a trainer.");
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            dao.UserDao userDao = new dao.UserDaoImpl();
            return userDao.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
