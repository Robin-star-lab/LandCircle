package com.example.landcircle;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.landcircle.models.ApiService;
import com.example.landcircle.models.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Surveyors extends AppCompatActivity {



    private TextInputEditText nameEditText;
    private TextInputEditText secondNameEditText;
    private TextInputEditText phoneNumberEditText;
    private TextInputEditText nationalIdEditText;
    private TextInputEditText postalAddressEditText;
    private TextInputEditText physicalAddressEditText;
    private TextInputEditText lsbRegNumberEditText;
    private TextInputEditText iskMembershipNumberEditText;
    Button btnPick,submitButton;
    private ApiService apiService;

    private Uri selectedImageUri;

    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();

                }
            });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveyors);


        btnPick      = findViewById(R.id.btnPickImage);
        submitButton    = findViewById(R.id.submit);

        nameEditText      = findViewById(R.id.first_name);
        secondNameEditText     = findViewById(R.id.second_name);
        phoneNumberEditText      = findViewById(R.id.phone_number);
        nationalIdEditText = findViewById(R.id.national_id);
        postalAddressEditText   = findViewById(R.id.postal_address_number);
        physicalAddressEditText = findViewById(R.id.physical_address_number);
        lsbRegNumberEditText   = findViewById(R.id.lsb_reg_number);
        iskMembershipNumberEditText     = findViewById(R.id.isk_membership_number);

        btnPick.setOnClickListener(v -> openGallery());
        submitButton.setOnClickListener(v -> submitForm());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }

    private void submitForm() {
        String firstName      = nameEditText.getText().toString().trim();
        String secondName     = secondNameEditText.getText().toString().trim();
        String phone          = phoneNumberEditText.getText().toString().trim();
        String nationalId     = nationalIdEditText.getText().toString().trim();
        String postalAddress = postalAddressEditText.getText().toString().trim();
        String physicalAddress = physicalAddressEditText.getText().toString().trim();
        String lsbRegNumber = lsbRegNumberEditText.getText().toString().trim();
        String iskMembershipNumber = iskMembershipNumberEditText.getText().toString().trim();

        if (firstName.isEmpty() ||
                secondName.isEmpty() ||
                phone.isEmpty() ||
                nationalId.isEmpty() ||
                postalAddress.isEmpty() ||
                physicalAddress.isEmpty() ||
                lsbRegNumber.isEmpty() ||
                iskMembershipNumber.isEmpty()) {
            Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedImageUri == null) {
            Toast.makeText(this, "Please pick a profile photo", Toast.LENGTH_SHORT).show();
            return;
        }

        // Build parts
        RequestBody rbFirst      = textPart(firstName);
        RequestBody rbSecond     = textPart(secondName);
        RequestBody rbPhone      = textPart(phone);
        RequestBody rbNationalId = textPart(nationalId);
        RequestBody rbPostalAddress   = textPart(postalAddress);
        RequestBody rbPhysicalAddress  = textPart(physicalAddress);
        RequestBody rbLsbRegNumber   = textPart(lsbRegNumber);
        RequestBody rbIskMembershipNumber     = textPart(iskMembershipNumber);

        MultipartBody.Part photoPart = filePartFromUri("profile_photo", selectedImageUri);

        String token = getSharedPreferences("MyApp", MODE_PRIVATE)
                .getString("accessToken", null);
        if (token == null) {
            Toast.makeText(Surveyors.this, "No token found", Toast.LENGTH_SHORT).show();
        }
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        apiService.registerSurveyors(
                "Bearer " + token,
                rbFirst, rbSecond, rbPhone, rbNationalId,
                rbPostalAddress,rbPhysicalAddress, rbLsbRegNumber, rbIskMembershipNumber,
                photoPart
        ).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful() ) {

                    Toast.makeText(Surveyors.this, "Registration successfull",Toast.LENGTH_LONG).show();



                    Intent intent = new Intent(Surveyors.this, HomePage.class); // Replace HomepageActivity.class with your actual homepage Activity
                    // Optional: Clear the back stack so the user doesn't go back to the registration form
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(Surveyors.this,
                            "Server error: " + response.code(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Surveyors.this,
                        "Failed: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private RequestBody textPart(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value == null ? "" : value);
    }

    /**
     * Safely turn a content Uri (from gallery) into a MultipartBody.Part by copying it to cache.
     * This avoids needing legacy file path APIs or storage permissions.
     */
    private MultipartBody.Part filePartFromUri(String partName, Uri uri) {
        try {
            ContentResolver cr = getContentResolver();
            String fileName = "upload_" + System.currentTimeMillis() + ".jpg";
            File cacheFile = new File(getCacheDir(), fileName);

            try (InputStream is = cr.openInputStream(uri);
                 FileOutputStream os = new FileOutputStream(cacheFile)) {
                byte[] buffer = new byte[8 * 1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                os.flush();
            }

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), cacheFile);
            return MultipartBody.Part.createFormData(partName, cacheFile.getName(), reqFile);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Image read error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
