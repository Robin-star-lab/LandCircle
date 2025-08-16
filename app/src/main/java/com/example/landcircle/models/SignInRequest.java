package com.example.landcircle.models;

public class SignInRequest {
    private String email;
    private String password_hash;

    public SignInRequest(String email, String password_hash) {
        this.email = email;
        this.password_hash = password_hash;
    }
}
