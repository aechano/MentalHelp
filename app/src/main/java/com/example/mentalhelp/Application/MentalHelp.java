package com.example.mentalhelp.Application;

import android.app.Application;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.mentalhelp.Model.MusicListModel;

public class MentalHelp extends Application {
    MediaPlayer mediaPlayer;
    MusicListModel musicListModel;
    static MentalHelp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        instance = this;
    }

    public void setMusicListModel(MusicListModel musicListModel) {
        this.musicListModel = musicListModel;
        musicStop();
        this.mediaPlayer = MediaPlayer.create(this, musicListModel.getPath());
    }

    public MusicListModel getMusicListModel() {
        return musicListModel;
    }

    public static MentalHelp getInstance() {
        return instance;
    }

    // Playing the music
    public void musicPlay() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    // Pausing the music
    public void musicPause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    // Stopping the music
    public void musicStop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
