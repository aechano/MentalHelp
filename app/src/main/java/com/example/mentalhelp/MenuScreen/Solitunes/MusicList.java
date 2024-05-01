package com.example.mentalhelp.MenuScreen.Solitunes;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalhelp.Adapter.MusicListAdapter;
import com.example.mentalhelp.Database.DB;
import com.example.mentalhelp.Database.Objects.Music;
import com.example.mentalhelp.Model.MusicListModel;
import com.example.mentalhelp.R;

import java.util.ArrayList;

public class MusicList extends AppCompatActivity {

    RecyclerView recyclerView;
    MusicListAdapter musicListAdapter;
    ArrayList<MusicListModel> musicListModelArrayList;
    private FrameLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        // Initialize your views
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

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

        DB db = new DB(getApplicationContext());
        musicListModelArrayList = new ArrayList<>();

        for (Music music : db.getAllMusic()){
            musicListModelArrayList.add(new MusicListModel(music.getTitle(), music.getMusic()));
        }

        // Check if there is data
        if (musicListModelArrayList.isEmpty()) {
            // If there is no data, show the progress bar and hide the RecyclerView
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            // If there is data, hide the progress bar and show the RecyclerView
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            // Populate your RecyclerView with data
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            musicListAdapter = new MusicListAdapter(musicListModelArrayList, this);
            recyclerView.setAdapter(musicListAdapter);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();

        // Handle the back arrow click
        if (id == android.R.id.home) {
            // Navigate back to previous activity or finish the current activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}