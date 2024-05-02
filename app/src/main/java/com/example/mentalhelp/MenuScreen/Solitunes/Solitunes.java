package com.example.mentalhelp.MenuScreen.Solitunes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mentalhelp.Application.MentalHelp;
import com.example.mentalhelp.Model.MusicListModel;
import com.example.mentalhelp.R;

public class Solitunes extends AppCompatActivity {

    MentalHelp mentalHelp;

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

        TextView textView = findViewById(R.id.mp3_player);
        textView.setText(musicListModel.getTitle());
        mentalHelp.musicPlay();
    }
}