package com.example.mentalhelp.List;

import androidx.appcompat.app.ActionBar;
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

import com.example.mentalhelp.Adapter.GuideListAdapter;
import com.example.mentalhelp.MenuScreen.Guides;
import com.example.mentalhelp.Model.GuideListModel;
import com.example.mentalhelp.R;

import java.util.ArrayList;

public class GuideList extends AppCompatActivity implements GuideListAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    GuideListAdapter guideListAdapter;
    ArrayList<GuideListModel> guideListModelArrayList;
    private FrameLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_list);

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
        SpannableString spannableString = new SpannableString("Guides");
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.deluge)), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);

        guideListModelArrayList = new ArrayList<>();

        guideListModelArrayList.add(new GuideListModel("My title is too long as it can go to another universe because it is very very long."));
        guideListModelArrayList.add(new GuideListModel("How to fight anxiety"));

        // Check if there is data
        if (guideListModelArrayList.isEmpty()) {
            // If there is no data, show the progress bar and hide the RecyclerView
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            // If there is data, hide the progress bar and show the RecyclerView
            progressBar.setVisibility(View.GONE);
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
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click here
        // For example, start the Guides activity with intent
        Intent intent = new Intent(this, Guides.class);
        startActivity(intent);
    }
}
