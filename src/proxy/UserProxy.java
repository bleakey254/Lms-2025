package proxy;

import dao.UserDao;
import model.User;
import java.sql.SQLException;

public class UserProxy implements UserDao {
    private final UserDao realUserDao;
    private final boolean hasAccess;

    public UserProxy(UserDao realUserDao, boolean hasAccess) {
        this.realUserDao = realUserDao;
        this.hasAccess = hasAccess;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        if (!hasAccess) {
            throw new SecurityException("Access denied to get user by id.");
        }
        return realUserDao.getUserById(id);
    }

    @Override
    public int getUserIdByCredentials(String email, String password) throws SQLException {
        if (!hasAccess) {
            throw new SecurityException("Access denied to get user id by credentials.");
        }
        return realUserDao.getUserIdByCredentials(email, password);
    }

    @Override
    public User getUserByCredentials(String email, String password) throws SQLException {
        if (!hasAccess) {
            throw new SecurityException("Access denied to get user by credentials.");
        }
        return realUserDao.getUserByCredentials(email, password);
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        if (!hasAccess) {
            throw new SecurityException("Access denied to add user.");
        }
        return realUserDao.addUser(user);
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        if (!hasAccess) {
            throw new SecurityException("Access denied to update user.");
        }
        return realUserDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        if (!hasAccess) {
            throw new SecurityException("Access denied to delete user.");
        }
        return realUserDao.deleteUser(id);
    }
}
