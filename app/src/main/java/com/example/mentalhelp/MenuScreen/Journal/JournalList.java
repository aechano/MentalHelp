package com.example.mentalhelp.MenuScreen.Journal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mentalhelp.Adapter.JournalListAdapter;
import com.example.mentalhelp.Application.MentalHelp;
import com.example.mentalhelp.Database.DB;
import com.example.mentalhelp.MenuScreen.DashBoard;
import com.example.mentalhelp.Model.JournalListModel;
import com.example.mentalhelp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class JournalList extends AppCompatActivity {

    RecyclerView recyclerView;
    JournalListAdapter journalListAdapter;
    ArrayList<JournalListModel> journalListModelArrayList;
    private TextView emptyTextView;
    private FloatingActionButton fabAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(MentalHelp.getInstance().getCurrentTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_list);

        // Initialize your views
        recyclerView = findViewById(R.id.recyclerView);
        emptyTextView = findViewById(R.id.emptyTextView);
        fabAdd = findViewById(R.id.fab_add);

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
        SpannableString spannableString = new SpannableString("Hidden Blues");
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(elementColor), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);

        // Initialize your list of journal entries
        DB db = new DB(getApplicationContext());
        journalListModelArrayList = db.getAllJournals();

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
            journalListAdapter.setOnClickListener(new JournalListAdapter.OnClick() {
                @Override
                public void onClick(JournalListModel journalListModel) {
                    Intent intent = new Intent(getApplicationContext(), AddJournal.class);
                    intent.putExtra("JOURNALLIST", "");
                    intent.putExtra("EDITING", journalListModel.getId());
                    intent.putExtra("Title", journalListModel.getTitle());
                    intent.putExtra("TimeCreated", journalListModel.getDateCreated());
                    intent.putExtra("TimeModified", journalListModel.getDateModified());
                    intent.putExtra("Content", journalListModel.getContent());
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onDelete(JournalListModel journalListModel, Integer position) {
                    DB db = new DB(getApplicationContext());
                    Integer rowsAffected = db.deleteJournal(journalListModel.getId());
                    if (rowsAffected != 0) {
                        Toast.makeText(getApplicationContext(),
                                "Journal entry deleted successfully.",
                                Toast.LENGTH_SHORT).show();
                    }
                    journalListModelArrayList.remove((int) position);
                    journalListAdapter.notifyDataSetChanged();
                }
            });
        }


        // Set click listener for FAB
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddJournal.class);
                intent.putExtra("JOURNALLIST", "");
                startActivity(intent);
                finish();
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

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}