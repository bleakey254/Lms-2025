package strategy;

public interface RegistrationStrategy {
    int register(String email, String password); // returns new user ID or -1
    String getRole(); // e.g., "Trainer", "Auditor"
}
