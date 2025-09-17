package model;

public class Trainer extends User {
    private String specialization;

    private Trainer(Builder b) {
        super(b.id, b.firstname, b.lastname, b.middlename,
              b.username, b.password, b.email, b.phoneNumber);
        this.usertype = "Trainer";
        this.specialization = b.specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public static class Builder {
        private int id;
        private String firstname, lastname, middlename;
        private String username, password, email, phoneNumber;
        private String specialization;

        public Builder setId(int id) { this.id = id; return this; }
        public Builder setFirstname(String f) { this.firstname = f; return this; }
        public Builder setLastname(String l) { this.lastname = l; return this; }
        public Builder setMiddlename(String m) { this.middlename = m; return this; }
        public Builder setUsername(String u) { this.username = u; return this; }
        public Builder setPassword(String p) { this.password = p; return this; }
        public Builder setEmail(String e) { this.email = e; return this; }
        public Builder setPhoneNumber(String pn) { this.phoneNumber = pn; return this; }
        public Builder setSpecialization(String s) { this.specialization = s; return this; }

        public Trainer build() {
            return new Trainer(this);
        }
    }
}
