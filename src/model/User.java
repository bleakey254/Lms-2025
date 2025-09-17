package model;

public abstract class User {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String middleName;
    protected String email;
    protected String username;
    protected String password;
    protected String usertype;
    protected String phoneNumber;

    protected User(int id, String firstName, String lastName, String middleName,
                  String username, String password, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMiddleName() { return middleName; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getUsertype() { return usertype; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }
}
