package edu.utn.entity;


public class Login {

    private String email;
    private String password;

    public Login(String email, String password) {
        setPassword(password);
        setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pasword) {
        this.password = pasword;
    }
}
