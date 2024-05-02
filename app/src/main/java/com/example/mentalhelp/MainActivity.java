package com.example.mentalhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.WindowManager;

import com.example.mentalhelp.Database.DB;
import com.example.mentalhelp.Form.RegistrationForm;
import com.example.mentalhelp.MenuScreen.DashBoard;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    private DB db;
    private boolean isFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the status bar color to #2a07df
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.melrose)); // Create a color resource

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

        // run loading processes
        initializeDatabase();
    }

    private void initializeDatabase() {
        db = new DB(getApplicationContext());
        db.populateMusicTable();
        db.populateGuidesTable();
    }
}