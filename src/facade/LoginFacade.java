package facade;

import model.User;
import strategy.LoginStrategy;

public class LoginFacade {
    private final LoginStrategy loginStrategy;

    public LoginFacade(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    public User login(String email, String password) throws Exception {
        if (email == null || password == null || email.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Email or password cannot be empty");
        }

        int userId = loginStrategy.login(email.trim(), password.trim());

        if (userId == -1 || userId == 0) {
            throw new Exception("Invalid email or password");
        }

        // 👇 Use strategy to load the correct User subclass (Trainer or Auditor)
        User user = loginStrategy.getUserById(userId);

        if (user == null) {
            throw new Exception("User not found in database");
        }

        return user;
    }
}
