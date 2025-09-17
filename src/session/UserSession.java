package session;

import model.User;

public class UserSession {
    private static UserSession instance;
    private User loggedInUser;

    // Private constructor to prevent instantiation
    private UserSession() {}

    // Singleton access method
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void clearSession() {
        loggedInUser = null;
    }
}
