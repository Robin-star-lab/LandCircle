package com.example.landcircle;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        ImageButton sell = findViewById(R.id.add_btn);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, Sellers.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner roleSpinner = findViewById(R.id.role_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        //Handle selection
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRole = parent.getItemAtPosition(position).toString();
                // Use HomePage.this as the context for the Toast
                Toast.makeText(HomePage.this, "Selected: " + selectedRole, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0: // Lawyer
                        Toast.makeText(getApplicationContext(), "Lawyer selected", Toast.LENGTH_SHORT).show();
                        break;
                    case 1: // Surveyor
                        Toast.makeText(getApplicationContext(), "Surveyor selected", Toast.LENGTH_SHORT).show();
                        break;
                    case 2: // Valuer
                        Toast.makeText(getApplicationContext(), "Valuer selected", Toast.LENGTH_SHORT).show();
                        break;
                    case 3: // Buyer
                        Toast.makeText(getApplicationContext(), "Buyer selected", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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