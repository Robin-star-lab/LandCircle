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
import android.util.Log;
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

public class Chief extends AppCompatActivity {



    private TextInputEditText nameEditText;
    private TextInputEditText secondNameEditText;
    private TextInputEditText phoneNumberEditText;
    private TextInputEditText nationalIdEditText;
    private TextInputEditText personalFileNumberEditText;
    private TextInputEditText stampSampleEditText;
    private TextInputEditText postalAddressEditText;
    private TextInputEditText  sublocationNameEditText;
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
        setContentView(R.layout.activity_chief);


        btnPick      = findViewById(R.id.btnPickImage);
        submitButton    = findViewById(R.id.submit);

        nameEditText      = findViewById(R.id.first_name);
        secondNameEditText     = findViewById(R.id.second_name);
        phoneNumberEditText      = findViewById(R.id.phone_number);
        nationalIdEditText = findViewById(R.id.national_id);
        personalFileNumberEditText   = findViewById(R.id.personal_file_number);
        stampSampleEditText = findViewById(R.id.stamp_sample);
        postalAddressEditText   = findViewById(R.id.postal_address_number);
        sublocationNameEditText     = findViewById(R.id.sublocation_name);

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
        String personalFile = personalFileNumberEditText.getText().toString().trim();
        String stampSample = stampSampleEditText.getText().toString().trim();
        String postalAddress = postalAddressEditText.getText().toString().trim();
        String sublocationName = sublocationNameEditText.getText().toString().trim();

        if (firstName.isEmpty() ||
                secondName.isEmpty() ||
                phone.isEmpty() ||
                nationalId.isEmpty() ||
                personalFile.isEmpty() ||
                stampSample.isEmpty() ||
                postalAddress.isEmpty() ||
                sublocationName.isEmpty()) {
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
        RequestBody rbPersonalFile   = textPart(personalFile);
        RequestBody rbStampSample  = textPart(stampSample);
        RequestBody rbPostalAddress   = textPart(postalAddress);
        RequestBody rbSublocationName     = textPart(sublocationName);

        MultipartBody.Part photoPart = filePartFromUri("profile_photo", selectedImageUri);

        String token = getSharedPreferences("MyApp", MODE_PRIVATE)
                .getString("accessToken", null);
        if (token == null) {
            Toast.makeText(Chief.this, "No token found", Toast.LENGTH_SHORT).show();
        }

        // In Chief.java, before the enqueue call
        androidx.media3.common.util.Log.d("ChiefApp_Request", "Token: Bearer " + token);
        androidx.media3.common.util.Log.d("ChiefApp_Request", "First Name: " + firstName);
        androidx.media3.common.util.Log.d("ChiefApp_Request", "Second Name: " + secondName);
        androidx.media3.common.util.Log.d("ChiefApp_Request", "Phone: " + phone);
        androidx.media3.common.util.Log.d("ChiefApp_Request", "National ID: " + nationalId);
        androidx.media3.common.util.Log.d("ChiefApp_Request", "Personal file: " + personalFile);
        androidx.media3.common.util.Log.d("ChiefApp_Request", "Stamp sample: " + stampSample);
        androidx.media3.common.util.Log.d("ChiefApp_Request", "Postal Address: " + postalAddress);
        androidx.media3.common.util.Log.d("ChiefApp_Request", "Sublocation Name: " + sublocationName);
        androidx.media3.common.util.Log.d("ChiefApp_Request", "Image URI: " + (selectedImageUri != null ? selectedImageUri.toString() : "null"));



        if (photoPart == null && selectedImageUri != null) {
            androidx.media3.common.util.Log.e("ChiefApp_Request", "photoPart is null even though selectedImageUri is not! Check filePartFromUri.");
        } else if (selectedImageUri == null) {
            androidx.media3.common.util.Log.e("ChiefApp_Request", "selectedImageUri is null, so photoPart will be an issue.");
        }

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        apiService.registerChief(
                "Bearer " + token,
                rbFirst, rbSecond, rbPhone, rbNationalId,
                rbPersonalFile, rbStampSample, rbPostalAddress, rbSublocationName,
                photoPart
        ).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful() ) {

                    Toast.makeText(Chief.this, "Registration successfull",Toast.LENGTH_LONG).show();



                    Intent intent = new Intent(Chief.this, HomePage.class); // Replace HomepageActivity.class with your actual homepage Activity
                    // Optional: Clear the back stack so the user doesn't go back to the registration form
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(Chief.this,
                            "Server error: " + response.code(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Chief.this,
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
