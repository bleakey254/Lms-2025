package dao;

import model.Trainer;
import model.Auditor;
import model.User;
import model.DBConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    @Override
    public User getUserByCredentials(String emailOrUsername, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
        Connection conn = DBConnection.getInstance().getConnection(); // No try-with-resources for conn
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emailOrUsername);
            ps.setString(2, emailOrUsername);
            ps.setString(3, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    logger.info("User found for login: " + rs.getString("username"));
                    return buildUserFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while fetching user by credentials", e);
            throw e;
        }
        return null;
    }

    @Override
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE idUsers = ?";
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    logger.info("User loaded by ID: " + userId);
                    return buildUserFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while fetching user by ID", e);
            throw e;
        }
        return null;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, firstname, middlename, lastname, mobilenumber, usertype) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()); // 🔐 TODO: Hash before storing
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getMiddleName());
            ps.setString(6, user.getLastName());
            ps.setString(7, user.getPhoneNumber());
            ps.setString(8, user.getUsertype());

            boolean inserted = ps.executeUpdate() > 0;
            if (inserted) logger.info("User added: " + user.getUsername());
            return inserted;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while adding user: " + user.getUsername(), e);
            throw e;
        }
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username=?, password=?, email=?, firstname=?, middlename=?, lastname=?, mobilenumber=?, usertype=? " +
                "WHERE idUsers=?";
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getMiddleName());
            ps.setString(6, user.getLastName());
            ps.setString(7, user.getPhoneNumber());
            ps.setString(8, user.getUsertype());
            ps.setInt(9, user.getId());

            boolean updated = ps.executeUpdate() > 0;
            if (updated) logger.info("User updated: " + user.getUsername());
            return updated;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while updating user: " + user.getUsername(), e);
            throw e;
        }
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE idUsers = ?";
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            boolean deleted = ps.executeUpdate() > 0;
            if (deleted) logger.info("User deleted with ID: " + id);
            return deleted;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while deleting user ID: " + id, e);
            throw e;
        }
    }

    @Override
    public int getUserIdByCredentials(String emailOrUsername, String password) throws SQLException {
        String sql = "SELECT idUsers FROM users WHERE (username = ? OR email = ?) AND password = ?";
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emailOrUsername);
            ps.setString(2, emailOrUsername);
            ps.setString(3, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("idUsers");
                    logger.info("User ID found for login: " + id);
                    return id;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while fetching user ID by credentials", e);
            throw e;
        }
        return -1; // Not found
    }

    private User buildUserFromResultSet(ResultSet rs) throws SQLException {
        String usertype = rs.getString("usertype");

        if ("Trainer".equalsIgnoreCase(usertype)) {
            return new Trainer.Builder()
                    .setId(rs.getInt("idUsers"))
                    .setUsername(rs.getString("username"))
                    .setPassword(rs.getString("password"))
                    .setEmail(rs.getString("email"))
                    .setFirstname(rs.getString("firstname"))
                    .setMiddlename(rs.getString("middlename"))
                    .setLastname(rs.getString("lastname"))
                    .setPhoneNumber(rs.getString("mobilenumber"))
                    .build();
        } else if ("Auditor".equalsIgnoreCase(usertype)) {
            return new Auditor.Builder()
                    .setId(rs.getInt("idUsers"))
                    .setUsername(rs.getString("username"))
                    .setPassword(rs.getString("password"))
                    .setEmail(rs.getString("email"))
                    .setFirstname(rs.getString("firstname"))
                    .setMiddlename(rs.getString("middlename"))
                    .setLastname(rs.getString("lastname"))
                    .setPhoneNumber(rs.getString("mobilenumber"))
                    .build();
        }

        throw new SQLException("Unknown usertype: " + usertype);
    }
}