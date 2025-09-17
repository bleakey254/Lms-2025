package strategy;

import dao.UserDao;
import model.Auditor;
import model.User;
import view.LoginPageAuditor;

import javax.swing.*;

public class AuditorLoginStrategy implements RoleLoginStrategy, LoginStrategy {

    @Override
    public void login(JFrame parent) {
        SwingUtilities.invokeLater(() -> new LoginPageAuditor(parent, this));
    }

    @Override
    public int login(String email, String password) {
        try {
            dao.UserDao userDao = new dao.UserDaoImpl();
            int id = userDao.getUserIdByCredentials(email, password);
            if (id <= 0) return 0;

            User user = userDao.getUserById(id);
            if (user instanceof Auditor) {
                System.out.println("✅ Logged in as Auditor ID: " + id);
                return id;
            } else {
                System.err.println("❌ Not an auditor.");
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
