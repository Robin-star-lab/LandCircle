package com.example.landcircle;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Professionals extends AppCompatActivity {

    Spinner spinner;
    LinearLayout lawyerForm, surveyorForm, valuerForm,physicalPlanerForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_professionals);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinner = findViewById(R.id.mySpinner);
        lawyerForm = findViewById(R.id.lawyerForm);
        surveyorForm = findViewById(R.id.surveyorForm);
        valuerForm = findViewById(R.id.valuerForm);
        physicalPlanerForm = findViewById(R.id.physicalPlannerForm);

        String[] roles = {"Select Role", "Lawyer", "Surveyor", "Valuer"};

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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }
}