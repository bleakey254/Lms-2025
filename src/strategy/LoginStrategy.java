package strategy;

import model.User;

public interface LoginStrategy {
    int login(String email, String password);

    User getUserById(int userId); // 👈 NEW METHOD
}
