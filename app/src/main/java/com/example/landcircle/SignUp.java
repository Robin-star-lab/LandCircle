package com.example.landcircle;
import com.example.landcircle.models.User;

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

public class SignUp extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    ApiService apiService;
    Button okButton; // You already declared it, no need to declare again inside onCreate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailEditText = findViewById(R.id.EmailAddress);
        passwordEditText = findViewById(R.id.Password);
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);


        okButton = findViewById(R.id.ok); // Initialize the class member okButton
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override // Add Override annotation
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password_hash = passwordEditText.getText().toString().trim();


                if (email.isEmpty() || password_hash.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                User user = new User(email, password_hash);
                signupUser(user);
            }
        });
    }
    private void signupUser(User user) {
        apiService.signup(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {

                    // Save email in SharedPreferences after signup
                    SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", user.getEmail());   // save the email
                    editor.apply();

                    Intent i = new Intent(SignUp.this,SignIn.class);

                    startActivity(i);


                    Toast.makeText(SignUp.this, "Signup successful!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SignUp.this, "Signup failed: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignUp.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

