package com.example.mentalhelp.MenuScreen.Guides;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mentalhelp.Adapter.GuideListAdapter;
import com.example.mentalhelp.Database.DB;
import com.example.mentalhelp.Database.Objects.Music;
import com.example.mentalhelp.MenuScreen.Guides.Guides;
import com.example.mentalhelp.Model.GuideListModel;
import com.example.mentalhelp.Model.MusicListModel;
import com.example.mentalhelp.R;

import java.util.ArrayList;

public class GuideList extends AppCompatActivity implements GuideListAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    GuideListAdapter guideListAdapter;
    ArrayList<GuideListModel> guideListModelArrayList;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_list);

        // Initialize your views
        recyclerView = findViewById(R.id.recyclerView);
        emptyTextView = findViewById(R.id.emptyTextView);

        // Set up your toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the status bar color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.melrose));

        // Create a SpannableString for the title
        SpannableString spannableString = new SpannableString("Wits' End");
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.deluge)), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);

        DB db = new DB(getApplicationContext());

        guideListModelArrayList = new ArrayList<>();

        guideListModelArrayList.addAll(db.getAllGuides());

        // Check if there is data
        if (guideListModelArrayList.isEmpty()) {
            // If there is no data, show the empty text and hide the RecyclerView
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            // If there is data, hide the empty text and show the RecyclerView
            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            // Populate your RecyclerView with data
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            guideListAdapter = new GuideListAdapter(guideListModelArrayList, this, this);
            recyclerView.setAdapter(guideListAdapter);
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
    public void onItemClick(GuideListModel guideListModel) {
        if (guideListModel == null) return; // Bad Gateway: No guide chosen.
        if (guideListModel.getPath() == null)
            return; // Bad Request: No path indicated in the chosen model.
        Intent intent = new Intent(this, Guides.class);
        intent.putExtra("PATH", guideListModel.getPath());
        intent.putExtra("TITLE", guideListModel.getTitle());
        startActivity(intent);
    }
}
