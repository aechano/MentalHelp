package com.example.mentalhelp.Application;

import android.app.Application;
import android.media.MediaPlayer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.mentalhelp.Model.MusicListModel;

import java.util.ArrayList;
import java.util.List;

public class MentalHelp extends Application {
    MediaPlayer mediaPlayer;
    MusicListModel musicListModel;
    int musicPosition = -1;
    OnSongCompletion onSongCompletion;
    List<MusicListModel> musicListModelList;
    static MentalHelp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        instance = this;
        musicListModelList = new ArrayList<>();
    }

    public void setMusicListModel(MusicListModel musicListModel, int position) {
        this.musicPosition = position;
        this.musicListModel = musicListModel;
        musicStop();
        this.mediaPlayer = MediaPlayer.create(this, musicListModel.getPath());
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextMusic();
                if (onSongCompletion != null) onSongCompletion.onSongCompletion();
            }
        });
    }

    public void setOnSongCompletionListener(OnSongCompletion onSongCompletion){
        this.onSongCompletion = onSongCompletion;
    }

    public boolean nextMusic() {
        if (this.mediaPlayer == null) return false;
        musicPosition++;
        if (musicPosition == musicListModelList.size()){
            // this will become out of bounds
            Toast.makeText(getApplicationContext(), "This is the end of Solitunes Playlist! Thank you for listening!", Toast.LENGTH_SHORT).show();
            musicPosition = -1; // no position
            musicStop(); // stopped music
            this.musicListModel = null; // no chosen song
            return false;
        }
        this.musicListModel = musicListModelList.get(musicPosition);
        setMusicListModel(this.musicListModel, musicPosition);
        return true;
    }

    public void previousMusic() {
        if (this.mediaPlayer == null) return;
        musicPosition--;
        if (musicPosition == -1) musicPosition = 0; // Repeat on first position.
        this.musicListModel = musicListModelList.get(musicPosition);
        setMusicListModel(this.musicListModel, musicPosition);
    }

    public int getSongDuration() {
        if (this.mediaPlayer == null) return -1;
        if (this.mediaPlayer.getDuration() == -1) return -1;
        return this.mediaPlayer.getDuration();
    }

    public int getSongCurrentPosition() {
        if (this.mediaPlayer == null) return -1;
        if (this.mediaPlayer.getCurrentPosition() == -1) return -1;
        return this.mediaPlayer.getCurrentPosition();
    }

    public void setSongToPosition(int position) {
        if (this.mediaPlayer == null) return;
        this.mediaPlayer.seekTo(position);
    }

    public MusicListModel getMusicListModel() {
        return musicListModel;
    }

    public List<MusicListModel> getMusicListModelList() {
        return musicListModelList;
    }

    public void setMusicListModelList(List<MusicListModel> musicListModelList) {
        this.musicListModelList = musicListModelList;
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

    public interface OnSongCompletion {
        void onSongCompletion();
    }
}
