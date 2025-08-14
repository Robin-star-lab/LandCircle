package com.example.landcircle.models;
import com.example.landcircle.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/users") // Matches Node.js route
    Call<Void> signup(@Body User user);
}
