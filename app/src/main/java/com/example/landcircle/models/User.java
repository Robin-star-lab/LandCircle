package com.example.landcircle.models;

public class User {
    private String email;
    private String password_hash;

    public User(String email, String password_hash) {
        this.email = email;
        this.password_hash = password_hash;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password_hash;
    }
}
