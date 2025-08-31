package com.example.landcircle;

import static android.content.Context.MODE_PRIVATE;
import com.example.landcircle.models.SignInRequest;
import com.example.landcircle.models.User;
import com.example.landcircle.models.LogInResponse;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText; // Import EditText
import android.widget.Toast;    // Import Toast
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;

import com.example.landcircle.models.ApiService;
import com.example.landcircle.models.RetrofitClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response; // Import Response
public class SignIn extends AppCompatActivity {
    private EditText passwordEditText;
    private Button okbtn;
    private ApiService apiService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        String email = getIntent().getStringExtra("email");
        Toast.makeText(SignIn.this, "Welcome " + email, Toast.LENGTH_LONG).show();


        passwordEditText = findViewById(R.id.Password);
        okbtn = findViewById(R.id.okbtn);

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();

            }
        });
    }
    private void signInUser() {
        String enteredPassword = passwordEditText.getText().toString().trim();
        // Fetch email stored during signup

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String email = prefs.getString("email", null);


        if (enteredPassword.isEmpty()) {
            Toast.makeText(SignIn.this, "Password is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email == null) {
            Toast.makeText(SignIn.this, "No email found", Toast.LENGTH_SHORT).show();
        }

        // Create request with email + password

        Call<LogInResponse> call = apiService.signIn(new SignInRequest(email, enteredPassword));
        call.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // ✅ Navigate to HomePageActivity

                    // Extract token
                    SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
                    prefs.edit().putString("accessToken", response.body().getAccessToken()).apply();



                    Intent intent = new Intent(SignIn.this, HomePage.class);
                    startActivity(intent);
                    //intent.putExtra("userId", response.body().getUserId());
                    //intent.putExtra("email", response.body().getEmail());

                } else {
                    Toast.makeText(SignIn.this, "Invalid password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                Toast.makeText(SignIn.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
