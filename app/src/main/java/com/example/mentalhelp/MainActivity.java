package com.example.mentalhelp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mentalhelp.Application.MentalHelp;
import com.example.mentalhelp.Application.MentalHelp.Themes;
import com.example.mentalhelp.Database.DB;
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
        }, 8000);

        // run loading processes
        initializeDatabase();
        initializeTheme();

        // Load GIF into the View
        ImageView logoView = findViewById(R.id.logoView); // Change View to ImageView
        Glide.with(this).load(R.drawable.knowledge_unscreen).into(logoView);
    }

    private void initializeDatabase() {
        db = new DB(getApplicationContext());
        db.populateMusicTable();
        db.populateGuidesTable();
        db.populateTheme();
    }

    private void initializeTheme() {
        db = new DB(getApplicationContext());
        String theme = db.getTheme();
        if (!"DEFAULT".equals(theme)) {
            if (Themes.ROSE.name().equals(theme)) {
                MentalHelp.getInstance().changeTheme(Themes.ROSE);
            } else if (Themes.TROPICAL_BLUE.name().equals(theme)) {
                MentalHelp.getInstance().changeTheme(Themes.TROPICAL_BLUE);
            } else if (Themes.CHROME_WHITE.name().equals(theme)) {
                MentalHelp.getInstance().changeTheme(Themes.CHROME_WHITE);
            } else if (Themes.GOOSE.name().equals(theme)) {
                MentalHelp.getInstance().changeTheme(Themes.GOOSE);
            } else if (Themes.SWEETCORN.name().equals(theme)) {
                MentalHelp.getInstance().changeTheme(Themes.SWEETCORN);
            } else if (Themes.DELUGE.name().equals(theme)) {
                MentalHelp.getInstance().changeTheme(Themes.DELUGE);
            }
        }
    }
}