package com.example.mentalhelp.MenuScreen.Solitunes;

import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.example.mentalhelp.Application.MentalHelp;
import com.example.mentalhelp.Model.MusicListModel;
import com.example.mentalhelp.R;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Solitunes extends AppCompatActivity {

    MentalHelp mentalHelp;
    TextView currentTime;
    SeekBar musicSeekbar;
    Boolean isPlaying = false;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solitunes);

        mentalHelp = MentalHelp.getInstance();

        // Set up your toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the status bar color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.melrose));

        // Create a SpannableString for the title
        SpannableString spannableString = new SpannableString("Solitunes");
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.deluge)), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);

        MusicListModel musicListModel = mentalHelp.getMusicListModel();
        if (musicListModel == null) {
            // if musicListModel is null, then go back to MusicList as a song has not been selected.
            finish();
            Toast.makeText(getApplicationContext(), "Bad Gateway: A song has not been selected. Please select a song before proceeding here.", Toast.LENGTH_SHORT).show();
            return;
        }

        TextView songTitle = findViewById(R.id.mp3_player);
        TextView totalTime = findViewById(R.id.total_time);
        ImageView pausePlay = findViewById(R.id.play);
        currentTime = findViewById(R.id.current_time);
        musicSeekbar = findViewById(R.id.seekbar);

        long mils = mentalHelp.getSongDuration();

        // set maximum time
        totalTime.setText(millisToTimeString(mils));
        musicSeekbar.setMax(Math.toIntExact(mils));

        // set current time to 0:00
        currentTime.setText(millisToTimeString(0));
        musicSeekbar.setProgress(0);

        songTitle.setText(musicListModel.getTitle());

        mentalHelp.musicPlay();
        isPlaying = true;

        musicSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mentalHelp.setSongToPosition(progress);
                    currentTime.setText(millisToTimeString(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        pausePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    pausePlay.setImageDrawable(ResourcesCompat.getDrawable(getApplicationContext().getResources(), R.drawable.play_button, getTheme()));
                    mentalHelp.musicPause();
                } else {
                    pausePlay.setImageDrawable(ResourcesCompat.getDrawable(getApplicationContext().getResources(), R.drawable.play, getTheme()));
                    mentalHelp.musicPlay();
                }
                isPlaying = !isPlaying;
            }
        });

        handler = new Handler();

        // Create a new Runnable to update the TextView every second
        Runnable updateTime = new Runnable() {
            @Override
            public void run() {
                // Update the TextView with the current time or any desired text
                long currentTimeMillis = mentalHelp.getSongCurrentPosition();
                currentTime.setText(millisToTimeString(currentTimeMillis));
                musicSeekbar.setProgress(Math.toIntExact(currentTimeMillis));

                if (currentTimeMillis >= mentalHelp.getSongDuration()) {
                    return; // end
                }

                handler.postDelayed(this, 1000);
            }
        };

        // Start updating the TextView every second
        handler.post(updateTime);
    }

    private String millisToTimeString(long time) {
        long min = Math.toIntExact((time / 1000) / 60);
        long sec = Math.toIntExact((time / 1000) % 60);
        return String.format(Locale.getDefault(), "%d:%02d", min, sec);
    }
}