package model;

public class Auditor extends User {

    public Auditor(Builder b) {
        super(b.id, b.firstname, b.lastname, b.middlename,
              b.username, b.password, b.email, b.phoneNumber);
        this.usertype = "Auditor";
    }

    public static class Builder {
        private int id;
        private String firstname, lastname, middlename;
        private String username, password, email, phoneNumber;

        public Builder setId(int id) { this.id = id; return this; }
        public Builder setFirstname(String f) { this.firstname = f; return this; }
        public Builder setLastname(String l)  { this.lastname  = l; return this; }
        public Builder setMiddlename(String m){ this.middlename = m; return this; }
        public Builder setUsername(String u)  { this.username  = u; return this; }
        public Builder setPassword(String p)  { this.password  = p; return this; }
        public Builder setEmail(String e)     { this.email     = e; return this; }
        public Builder setPhoneNumber(String pn) { this.phoneNumber = pn; return this; }

        public Auditor build() { return new Auditor(this); }
    }
}
