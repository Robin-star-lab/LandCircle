package com.example.landcircle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class SellerInput extends AppCompatActivity {

    TextInputEditText name, name_two, id_number, phone, password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_input); // We'll create this layout next

        // Optional: Setup a Toolbar
        /*Toolbar toolbar = findViewById(R.id.toolbar_add_land);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Add New Land");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back button
        }*/


        name = findViewById(R.id.name);
        name_two = findViewById(R.id.name_two);
        id_number = findViewById(R.id.id_number);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.Password);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(v -> {
            String firstName = name.getText().toString().trim();
            String secondName = name_two.getText().toString().trim();
            String idNumber = id_number.getText().toString().trim();
            String phoneNumber = phone.getText().toString().trim();
            String passwordNumber = password.getText().toString().trim();

            if (firstName.isEmpty()) {
                name.setError("Location cannot be empty");
                return;
            }
            if (secondName.isEmpty()) {
                name_two.setError("Size cannot be empty");
                return;
            }
            if (idNumber.isEmpty()) {
                id_number.setError("Price cannot be empty");
                return;
            }
            if (phoneNumber.isEmpty()) {
                phone.setError("Phone number cannot be empty");
                return;
            }
            if (passwordNumber.isEmpty()) {
                password.setError("Password cannot be empty");
                return;
            }

            //String message = "Land Details Submitted:\nLocation: " + location + "\nSize: " + size + "\nPrice: " + price;
            //Toast.makeText(SellerInput.this, message, Toast.LENGTH_LONG).show();

            // Here you would typically save the data (e.g., to a database, send to API)
            // and then potentially finish the activity
            finish(); // Closes this activity and returns to HomePage
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle the Toolbar's back button
        onBackPressed();
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        // Apply exit animation when this activity finishes
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

