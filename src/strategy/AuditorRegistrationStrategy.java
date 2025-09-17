package strategy;

import model.Auditor;

public class AuditorRegistrationStrategy implements RegistrationStrategy {

    @Override
    public int register(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password cannot be empty.");
        }
        if (email.endsWith("@auditor.com")) {
            int generatedId = (int) (Math.random() * 1000 + 1);  // Fake ID generation
            Auditor auditor = new Auditor.Builder()
                .setId(generatedId)
                .setEmail(email)
                .setPassword(password)
                .setFirstname("")
                .setLastname("")
                .setMiddlename("")
                .setUsername("")
                .setPhoneNumber("")
                .build();
            // Normally, you would save the auditor to the database here
            return generatedId;
        } else {
            throw new IllegalArgumentException("Only auditor emails allowed.");
        }
    }

    @Override
    public String getRole() {
        return "Auditor";
    }
}
