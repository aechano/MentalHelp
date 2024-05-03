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
    Boolean havePlayed = false;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        instance = this;
        musicListModelList = new ArrayList<>();
    }

    public void setMusicListModel(MusicListModel musicListModel, int position) {
        musicStop();
        this.havePlayed = false;
        this.musicPosition = position;
        this.musicListModel = musicListModel;
        this.mediaPlayer = MediaPlayer.create(this, musicListModel.getPath());
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextMusic();
                if (onSongCompletion != null) onSongCompletion.onSongCompletion();
            }
        });
    }

    public void setOnSongCompletionListener(OnSongCompletion onSongCompletion) {
        this.onSongCompletion = onSongCompletion;
    }

    public boolean nextMusic() {
        if (this.mediaPlayer == null) return false;
        musicPosition++;
        if (musicPosition == musicListModelList.size()) {
            // this will become out of bounds
            Toast.makeText(getApplicationContext(), "This is the end of Solitunes Playlist! Thank you for listening!", Toast.LENGTH_SHORT).show();
            musicStop(); // stopped music
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

    public Boolean getHavePlayed() {
        return havePlayed;
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
            havePlayed = true;
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
            this.musicListModel = null; // no chosen song
            musicPosition = -1; // no position
        }
    }

    public int musicState() {
        if (mediaPlayer == null) return -1; // no music in queue
        if (mediaPlayer.isPlaying()) return 1; // music is playing
        return 0; // music in queue, but paused.
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public interface OnSongCompletion {
        void onSongCompletion();
    }


}
