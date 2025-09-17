package dao;

import model.User;
import java.sql.SQLException;

public interface UserDao {
    User getUserById(int id) throws SQLException;
    int getUserIdByCredentials(String email, String password) throws SQLException;
    User getUserByCredentials(String email, String password) throws SQLException;
    boolean addUser(User user) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    boolean deleteUser(int id) throws SQLException;
}
