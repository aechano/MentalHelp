package com.example.mentalhelp.MenuScreen.Solitunes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.quickaccesswallet.GetWalletCardsCallback;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalhelp.Adapter.MusicListAdapter;
import com.example.mentalhelp.Application.MentalHelp;
import com.example.mentalhelp.Database.DB;
import com.example.mentalhelp.Database.Objects.Music;
import com.example.mentalhelp.Model.MusicListModel;
import com.example.mentalhelp.R;

import java.util.ArrayList;

public class MusicList extends AppCompatActivity {

    RecyclerView recyclerView;
    MusicListAdapter musicListAdapter;
    ArrayList<MusicListModel> musicListModelArrayList;
    ImageView pausePlay;
    ImageView previousSong;
    ImageView nextSong;
    TextView musicTitle;
    CardView playingControls;
    Long lastClickedPrevious = 0L;
    MentalHelp mentalHelp;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(MentalHelp.getInstance().getCurrentTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        // Initialize your views
        recyclerView = findViewById(R.id.recyclerView);
        emptyTextView = findViewById(R.id.emptyTextView);
        musicTitle = findViewById(R.id.music_title);
        nextSong = findViewById(R.id.next_song);
        previousSong = findViewById(R.id.previous_song);
        pausePlay = findViewById(R.id.pause_play);
        playingControls = findViewById(R.id.playing_controls);

        // Set up your toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TypedValue typedValueElement = new TypedValue();
        TypedValue typedValueBackground = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.background, typedValueBackground, true);
        getTheme().resolveAttribute(R.attr.element, typedValueElement, true);
        int backgroundColor = typedValueBackground.data;
        int elementColor = typedValueElement.data;

        // Set the status bar color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(elementColor);

        // Create a SpannableString for the title
        SpannableString spannableString = new SpannableString("Solitunes");
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(elementColor), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);

        mentalHelp = MentalHelp.getInstance();

        if (mentalHelp.musicState() == -1) {
            playingControls.setVisibility(View.GONE);
        } else {
            initializePlayingControlsUI();
        }

        DB db = new DB(getApplicationContext());
        musicListModelArrayList = new ArrayList<>();

        for (Music music : db.getAllMusic()) {
            musicListModelArrayList.add(new MusicListModel(music.getTitle(), music.getMusic()));
        }

        mentalHelp.setMusicListModelList(musicListModelArrayList);

        // Check if there is data
        if (musicListModelArrayList.isEmpty()) {
            // If there is no data, show the empty text and hide the RecyclerView
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            // If there is data, hide the empty text and show the RecyclerView
            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            // Populate your RecyclerView with data
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            musicListAdapter = new MusicListAdapter(musicListModelArrayList);
            recyclerView.setAdapter(musicListAdapter);
            musicListAdapter.setOnSongClickListener(new MusicListAdapter.OnSongClick() {
                @Override
                public void songClick(MusicListModel musicListModel, int position) {
                    mentalHelp.setMusicListModel(musicListModel, position);
                    Intent intent = new Intent(getApplicationContext(), Solitunes.class);
                    startActivity(intent);
                    if (mentalHelp.musicState() != -1) {
                        playingControls.setVisibility(View.VISIBLE);
                        initializePlayingControlsUI();
                    }
                }
            });
        }
    }

    public void initializePlayingControlsUI() {
        musicTitle.setText(mentalHelp.getMusicListModel().getTitle());
        pausePlay.setOnClickListener(v -> {
            if (mentalHelp.musicState() == 1) {
                pausePlay.setImageDrawable(ResourcesCompat.getDrawable(getApplicationContext().getResources(), R.drawable.play_button, getTheme()));
                mentalHelp.musicPause();
            } else {
                pausePlay.setImageDrawable(ResourcesCompat.getDrawable(getApplicationContext().getResources(), R.drawable.play, getTheme()));
                mentalHelp.musicPlay();
            }
        });

        previousSong.setOnClickListener(v -> {
            if (System.currentTimeMillis() - lastClickedPrevious < 2000) {
                mentalHelp.previousMusic();
                musicTitle.setText(mentalHelp.getMusicListModel().getTitle());
                mentalHelp.musicPlay();
            } else {
                // keep track of lastClickedPrevious
                lastClickedPrevious = System.currentTimeMillis();

                // reset song
                mentalHelp.setSongToPosition(0);
            }
        });

        nextSong.setOnClickListener(v -> {
            boolean successful = mentalHelp.nextMusic();
            if (successful) {
                musicTitle.setText(mentalHelp.getMusicListModel().getTitle());
                mentalHelp.musicPlay();
            } else {
                Handler newHandler = new Handler();
                newHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 500);
            }
        });
        mentalHelp.setOnSongCompletionListener(new MentalHelp.OnSongCompletion() {
            @Override
            public void onSongCompletion() {
                nextSong.performClick();
            }
        });
        playingControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Solitunes.class);
                startActivity(intent);
            }
        });
        if (mentalHelp.musicState() == 0) {
            pausePlay.setImageDrawable(ResourcesCompat.getDrawable(getApplicationContext().getResources(), R.drawable.play_button, getTheme()));
        } else {
            pausePlay.setImageDrawable(ResourcesCompat.getDrawable(getApplicationContext().getResources(), R.drawable.play, getTheme()));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();

        // Handle the back arrow click
        if (id == android.R.id.home) {
            // Navigate back to previous activity or finish the current activity
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mentalHelp.musicState() != -1) {
            initializePlayingControlsUI();
        }
    }
}