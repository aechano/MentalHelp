package com.example.mentalhelp.MenuScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.mentalhelp.MenuScreen.Journal.AddJournal;
import com.example.mentalhelp.MenuScreen.Journal.JournalList;
import com.example.mentalhelp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CardView themeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.settings);

        // Find the theme view
        themeView = findViewById(R.id.theme_view);

        // Set OnClickListener to theme view
        themeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show AlertDialog with theme options
                showThemeOptionsDialog();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);

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

        findViewById(R.id.contact_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, EmergencyContact.class));
            }
        });

        findViewById(R.id.about_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, AboutCompany.class));
            }
        });

        findViewById(R.id.app_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, AboutApp.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId(); // Get the selected item's ID
                if (id == R.id.dashboard) {
                    startActivity(new Intent(getApplicationContext(), DashBoard.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (id == R.id.calendar) {
                    startActivity(new Intent(getApplicationContext(), Calendar.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (id == R.id.add) {
                    startActivity(new Intent(getApplicationContext(), AddJournal.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (id == R.id.settings) {
                    ;
                    return true;
                }

                return false;


            }
        });
    }

    // Method to show AlertDialog with theme options
    private void showThemeOptionsDialog() {
        final String[] themes = {"Rose", "Tropical Blue", "Goose", "Chrome White", "Sweetcorn"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please select a theme");

        builder.setItems(themes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle theme selection here
                String selectedTheme = themes[which];
                // You can perform actions based on the selected theme
            }
        });

        builder.show();
    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}