package com.example.landcircle;

import static android.content.Context.MODE_PRIVATE;
import com.google.android.material.textfield.TextInputEditText;
import android.net.DnsResolver;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.landcircle.models.ApiService;
import com.example.landcircle.models.RetrofitClient;
import com.example.landcircle.models.ValuerRequest;

public class Valuers extends AppCompatActivity {

    private TextInputEditText nameEditText;
    private TextInputEditText secondNameEditText;
    private TextInputEditText phoneNumberEditText;
    private TextInputEditText nationalIdEditText;
    private TextInputEditText vrbRegNumberEditText;
    private TextInputEditText iskMembershipNumberEditText;
    private TextInputEditText physicalAddressEditText;
    private TextInputEditText postalAddressEditText;
    Button submitButton;
    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_valuers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameEditText = findViewById(R.id.first_name);
        secondNameEditText = findViewById(R.id.second_name);
        phoneNumberEditText = findViewById(R.id.phone_number);
        nationalIdEditText = findViewById(R.id.national_id);
        vrbRegNumberEditText = findViewById(R.id.vrb_reg_number);
        iskMembershipNumberEditText = findViewById(R.id.isk_membership_number);
        physicalAddressEditText = findViewById(R.id.physical_address_number);
        postalAddressEditText = findViewById(R.id.postal_address_number);

        //initialize api service
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        submitButton = findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = nameEditText.getText().toString().trim();
                String second_name = secondNameEditText.getText().toString().trim();
                String phone_number = phoneNumberEditText.getText().toString().trim();
                String national_id = nationalIdEditText.getText().toString().trim();
                String vrb_reg_number = vrbRegNumberEditText.getText().toString().trim();
                String isk_membership_number = iskMembershipNumberEditText.getText().toString().trim();
                String physical_address_number = physicalAddressEditText.getText().toString().trim();
                String postal_address_number = postalAddressEditText.getText().toString().trim();

                if (first_name.isEmpty() ||
                        second_name.isEmpty() ||
                        phone_number.isEmpty() ||
                        national_id.isEmpty() ||
                        vrb_reg_number.isEmpty() ||
                        isk_membership_number.isEmpty() ||
                        physical_address_number.isEmpty() ||
                        postal_address_number.isEmpty()) {
                    Toast.makeText(Valuers.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                ValuerRequest valuer = new ValuerRequest(first_name,
                        second_name,
                        phone_number,
                        national_id,
                        vrb_reg_number,
                        isk_membership_number,
                        physical_address_number,
                        postal_address_number);
                valuersRegistration(valuer);


            }
        });
    }

    private void valuersRegistration(ValuerRequest valuer) {
        String token = getSharedPreferences("MyApp", MODE_PRIVATE)
                .getString("accessToken", null);
        if (token == null) {
            Toast.makeText(Valuers.this, "No token found", Toast.LENGTH_SHORT).show();
        }
        apiService.valuersIn("Bearer " + token, valuer).enqueue(new Callback<Void>() {



            @Override
            public void onResponse(Call<Void>call , Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Valuers.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Valuers.this, HomePage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Valuers.this, "Registration failed: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Valuers.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}