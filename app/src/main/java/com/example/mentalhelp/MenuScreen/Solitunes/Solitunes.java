package com.example.mentalhelp.MenuScreen.Solitunes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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