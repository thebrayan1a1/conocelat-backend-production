package com.example.conocemelat.jwt.model;

public class AuthenticationResquest {

    private String email;
    private String password;

    public AuthenticationResquest() {
    }

    public AuthenticationResquest(String email, String password) {
        this.email = email;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
