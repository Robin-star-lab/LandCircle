package com.example.landcircle;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomePage extends AppCompatActivity {

    Spinner spinner;
    ImageButton add_btn;
    Button legal_ai;
    LinearLayout lawyerForm, surveyorForm, valuerForm,physicalPlanerForm,chiefForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        String email = getIntent().getStringExtra("email");
        Toast.makeText(this, "Welcome " + email, Toast.LENGTH_LONG).show();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        add_btn = findViewById(R.id.add_btn);
        if (add_btn != null) {
            add_btn.setOnClickListener(v -> {
                Intent intent = new Intent(HomePage.this, SellerInput.class);
                startActivity(intent);
                // Apply enter animation for new activity and exit animation for current activity
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });
        }

        legal_ai = findViewById(R.id.legal_ai);
        if (legal_ai != null) {
            legal_ai.setOnClickListener(v -> {
                Intent intent = new Intent(HomePage.this, ChatActivity.class);
                startActivity(intent);
                // Apply enter animation for new activity and exit animation for current activity
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });
        }
        spinner = findViewById(R.id.role_spinner);
        lawyerForm = findViewById(R.id.lawyerForm);
        surveyorForm = findViewById(R.id.surveyorForm);
        valuerForm = findViewById(R.id.valuerForm);
        physicalPlanerForm = findViewById(R.id.physicalPlannerForm);
        chiefForm = findViewById(R.id.chiefForm);

        String[] roles = {"Register as a", "Lawyer", "Surveyor", "Valuer","Physical Planner","Chief"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Hide all forms initially
                lawyerForm.setVisibility(View.GONE);
                surveyorForm.setVisibility(View.GONE);
                valuerForm.setVisibility(View.GONE);
                physicalPlanerForm.setVisibility(View.GONE);
                chiefForm.setVisibility(View.GONE);

                // Show form based on selection
                switch (position) {
                    case 1: // Lawyer
                        lawyerForm.setVisibility(View.VISIBLE);
                        break;
                    case 2: // Surveyor
                        surveyorForm.setVisibility(View.VISIBLE);
                        break;
                    case 3: // Valuer
                        valuerForm.setVisibility(View.VISIBLE);
                        break;
                    case 4: // Valuer
                        physicalPlanerForm.setVisibility(View.VISIBLE);
                        break;
                    case 5: // Valuer
                        chiefForm.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        //Handle selection


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This inflates the menu and creates the 3-dot icon automatically
        getMenuInflater().inflate(R.menu.menu, menu); // Ensure R.menu.menu_main exists
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId(); // Use a local variable for clarity and potential future use

        if (itemId == R.id.action_home) { // Ensure these IDs exist in menu_main.xml
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.action_settings) {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.action_profile) {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show(); // Corrected from "About clicked" if it's profile
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }





}