package com.example.mentalhelp.MenuScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mentalhelp.MenuScreen.Journal.AddJournal;
import com.example.mentalhelp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.settings);


        Toolbar toolbar = findViewById(R.id.toolbar);

        // Initialize the Spinner
        Spinner spinner = findViewById(R.id.spinner_colors);
        // Define the options
        String[] colors = {"Rose", "Tropical Blue", "Goose", "ChromeWhite", "Sweetcorn"};

        // Set the status bar color to #2a07df
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.melrose)); // Create a color resource

        setSupportActionBar(toolbar);

        // Create a SpannableString to make the title bold and set color
        SpannableString spannableString = new SpannableString("Settings");
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.deluge)), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the toolbar title text
        getSupportActionBar().setTitle(spannableString);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colors);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId(); // Get the selected item's ID
                if (id == R.id.dashboard) {
                    startActivity(new Intent(getApplicationContext(), DashBoard.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if (id == R.id.calendar){
                    startActivity(new Intent(getApplicationContext(), Calendar.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if (id == R.id.add){
                    startActivity(new Intent(getApplicationContext(), AddJournal.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if(id == R.id.settings){;
                    return true;
                }

                return false;


            }
        });

    }
}