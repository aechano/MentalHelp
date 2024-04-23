package com.example.mentalhelp.Application;

import android.app.Application;
import android.media.MediaPlayer;

public class MentalHelp extends Application {
    MediaPlayer mediaPlayer;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
