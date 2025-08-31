package com.example.landcircle.models;

public class LogInResponse {
    private String message;
    private String accessToken;
    private String refreshToken;
    private String userId;
    private String email;

    public String getMessage() {
        return message;
    }
    public String getUserId() {
        return userId;
    }
    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
