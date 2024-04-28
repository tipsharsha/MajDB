package com.oop.majdb;

public class SignupUser {
    private final String name;
    private final String password;
    private final String email;

    public SignupUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
