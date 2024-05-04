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
import android.view.View;
import android.view.WindowManager;

import com.example.mentalhelp.MenuScreen.Guides.GuideList;
import com.example.mentalhelp.List.JournalList;
import com.example.mentalhelp.MenuScreen.HappyFit.HappyFit;
import com.example.mentalhelp.MenuScreen.Solitunes.MusicList;
import com.example.mentalhelp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Toolbar toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        // Set the status bar color to #2a07df
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.melrose)); // Create a color resource

        setSupportActionBar(toolbar);

        // Create a SpannableString to make the title bold and set color
        SpannableString spannableString = new SpannableString("Dashboard");
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.deluge)), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the toolbar title text
        getSupportActionBar().setTitle(spannableString);

        // Set OnClickListener for Hidden Blues card
        findViewById(R.id.hiddenBlues).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this, JournalList.class));
            }
        });

        findViewById(R.id.music).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this, MusicList.class));
            }
        });

        findViewById(R.id.guides).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this, GuideList.class));
            }
        });

        findViewById(R.id.happyFit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this, HappyFit.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId(); // Get the selected item's ID
                if (id == R.id.dashboard) {
                    return true;
                }else if (id == R.id.calendar){
                    startActivity(new Intent(getApplicationContext(), Calendar.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if (id == R.id.add){
                    startActivity(new Intent(getApplicationContext(), AddJournal.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if(id == R.id.settings){
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    overridePendingTransition(0, 0);
                    return true;
                }

                return false;


            }
        });


    }
}