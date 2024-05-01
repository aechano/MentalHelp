package com.example.mentalhelp.Application;

import android.app.Application;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatDelegate;

public class MentalHelp extends Application {
    MediaPlayer mediaPlayer;
    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
