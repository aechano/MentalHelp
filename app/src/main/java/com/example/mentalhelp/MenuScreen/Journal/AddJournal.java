package com.example.mentalhelp.MenuScreen.Journal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mentalhelp.LinedEditText;
import com.example.mentalhelp.MenuScreen.Calendar;
import com.example.mentalhelp.MenuScreen.DashBoard;
import com.example.mentalhelp.MenuScreen.Settings;
import com.example.mentalhelp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormatSymbols;
import java.util.Locale;

public class AddJournal extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView dateText;
    LinedEditText linedEditTextExtra, noteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.add);

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

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId(); // Get the selected item's ID
                if (id == R.id.dashboard) {
                    startActivity(new Intent(getApplicationContext(), DashBoard.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if (id == R.id.calendar){
                    startActivity(new Intent(getApplicationContext(), Calendar.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if (id == R.id.add){
                    return true;
                }else if(id == R.id.settings){
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    overridePendingTransition(0, 0);
                    return true;
                }

                return false;


            }
        });

        dateText = findViewById(R.id.datetext);
        linedEditTextExtra = findViewById(R.id.extra_lines);
        noteContent = findViewById(R.id.note_text);

        linedEditTextExtra.setOnClickListener(v -> {
            noteContent.requestFocus();
            linedEditTextExtra.clearFocus();
            showKeyboard(noteContent);
        });
        dateText.setText(millisToDate(System.currentTimeMillis()));
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


    private void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private String millisToDate(Long millis) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        int mYear = calendar.get(java.util.Calendar.YEAR);
        int mMonth = calendar.get(java.util.Calendar.MONTH);
        int mDay = calendar.get(java.util.Calendar.DAY_OF_MONTH);

        // Convert numeric month to month name
        String[] monthNames = new DateFormatSymbols(Locale.ENGLISH).getMonths();
        String monthName = monthNames[mMonth];

        // Construct the date string in desired format

        return String.format(Locale.getDefault(), "%s %d, %d", monthName, mDay, mYear);
    }
}