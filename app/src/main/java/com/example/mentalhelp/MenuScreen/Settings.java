package com.example.mentalhelp.MenuScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

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