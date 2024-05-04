package com.example.mentalhelp.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.mentalhelp.Adapter.JournalListAdapter;
import com.example.mentalhelp.Model.JournalListModel;
import com.example.mentalhelp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class JournalList extends AppCompatActivity {

    RecyclerView recyclerView;
    JournalListAdapter journalListAdapter;
    ArrayList<JournalListModel> journalListModelArrayList;
    private TextView emptyTextView;
    private FloatingActionButton fabAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_list);

        // Initialize your views
        recyclerView = findViewById(R.id.recyclerView);
        emptyTextView = findViewById(R.id.emptyTextView);
        fabAdd = findViewById(R.id.fab_add);


        // Set up your toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the status bar color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.melrose));

        // Create a SpannableString for the title
        SpannableString spannableString = new SpannableString("Hidden Blues");
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.deluge)), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);

        // Initialize your list of journal entries
        journalListModelArrayList = new ArrayList<>();

        // Sample data for demonstration
        journalListModelArrayList.add(new JournalListModel("This is a long Title that I created which can go beyond the card view", "10-20-24", "I am not Happy because there is a lot of gawain and I'm tired, want to go to sleep. Please let me finish this ngayong week na huhu kasi a lot of gawain is waiting pa"));
        journalListModelArrayList.add(new JournalListModel("Their Title", "10-20-24", "I am Happy"));
        journalListModelArrayList.add(new JournalListModel("Me Title", "10-20-24", "I am good"));
        journalListModelArrayList.add(new JournalListModel("She Title", "10-20-24", "I am fabulous"));
        journalListModelArrayList.add(new JournalListModel("He Title", "10-20-24", "I can do it"));
        journalListModelArrayList.add(new JournalListModel("This is a long Title that I created which can go beyond the card view", "10-20-24", "I am not Happy because there is a lot of gawain and I'm tired, want to go to sleep. Please let me finish this ngayong week na huhu kasi a lot of gawain is waiting pa"));
        journalListModelArrayList.add(new JournalListModel("Their Title", "10-20-24", "I am Happy"));
        journalListModelArrayList.add(new JournalListModel("Me Title", "10-20-24", "I am good"));
        journalListModelArrayList.add(new JournalListModel("She Title", "10-20-24", "I am fabulous"));
        journalListModelArrayList.add(new JournalListModel("He Title", "10-20-24", "I can do it"));

        // Check if there is data
        if (journalListModelArrayList.isEmpty()) {
            // If there is no data, show the empty text and hide the RecyclerView
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            // If there is data, hide the empty text and show the RecyclerView
            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            // Populate your RecyclerView with data
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            journalListAdapter = new JournalListAdapter(journalListModelArrayList, this);
            recyclerView.setAdapter(journalListAdapter);
        }


        // Set click listener for FAB
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event for adding content
                // Add your logic here
            }
        });
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