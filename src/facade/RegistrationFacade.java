package facade;

import strategy.RegistrationStrategy;
import model.User;
import model.Trainer;
import model.Auditor;

public class RegistrationFacade {

    private final RegistrationStrategy strategy;

    public RegistrationFacade(RegistrationStrategy strategy) {
        this.strategy = strategy;
    }

    public User register(String email, String password) throws Exception {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new Exception("Email and password are required.");
        }

        int id = strategy.register(email, password);
        if (id == -1) {
            throw new Exception("Registration failed: user may already exist.");
        }

        String role = strategy.getRole();
        if ("Trainer".equalsIgnoreCase(role)) {
            return new Trainer.Builder()
                .setId(id)
                .setEmail(email)
                .setPassword(password)
                .setFirstname("")
                .setLastname("")
                .setMiddlename("")
                .setUsername("")
                .setPhoneNumber("")
                .build();
        } else if ("Auditor".equalsIgnoreCase(role)) {
            return new Auditor.Builder()
                .setId(id)
                .setEmail(email)
                .setPassword(password)
                .setFirstname("")
                .setLastname("")
                .setMiddlename("")
                .setUsername("")
                .setPhoneNumber("")
                .build();
        } else {
            throw new Exception("Unknown user role: " + role);
        }
    }
}
