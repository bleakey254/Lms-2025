package strategy;

import model.Trainer;

public class TrainerRegistrationStrategy implements RegistrationStrategy {

    @Override
    public int register(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password cannot be empty.");
        }
        if (email.endsWith("@trainer.com")) {
            int generatedId = (int) (Math.random() * 1000 + 1);  // Fake ID generation
            Trainer trainer = new Trainer.Builder()
                .setId(generatedId)
                .setEmail(email)
                .setPassword(password)
                .setFirstname("")
                .setLastname("")
                .setMiddlename("")
                .setUsername("")
                .setPhoneNumber("")
                .build();
            // Normally, you would save the trainer to the database here
            return generatedId;
        } else {
            throw new IllegalArgumentException("Only trainer emails allowed.");
        }
    }

    @Override
    public String getRole() {
        return "Trainer";
    }
}
