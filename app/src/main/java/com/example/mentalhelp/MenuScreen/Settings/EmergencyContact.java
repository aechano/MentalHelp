package com.example.mentalhelp.MenuScreen.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mentalhelp.Application.MentalHelp;
import com.example.mentalhelp.R;

public class EmergencyContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(MentalHelp.getInstance().getCurrentTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

        TextView link1 = findViewById(R.id.link1);
        link1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link2 = findViewById(R.id.link2);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

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
        SpannableString spannableString = new SpannableString("Emergency Contact");
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(elementColor), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);
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
}