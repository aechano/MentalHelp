package com.example.mentalhelp.MenuScreen.HappyFit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.mentalhelp.Application.MentalHelp;
import com.example.mentalhelp.R;

public class HappyFit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(MentalHelp.getInstance().getCurrentTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_fit);

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
        SpannableString spannableString = new SpannableString("Happy Fit");
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(elementColor), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(spannableString);

        WebView webView = findViewById(R.id.medintro);
        String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CmhkmLFc74E?si=EoIHfkakb19uEVoY\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView.loadData(video, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        WebView webView1 = findViewById(R.id.howmedworks);
        String video1 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/wTBSGgbIvsY?si=UZjFOIqRUoeqvsv9\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView1.loadData(video1, "text/html", "utf-8");
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebChromeClient(new WebChromeClient());

        WebView webView2 = findViewById(R.id.howtomed);
        String video2 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KQOAVZew5l8?si=UNbFWSt4sPRqSyw7\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView2.loadData(video2, "text/html", "utf-8");
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setWebChromeClient(new WebChromeClient());

        WebView webView3 = findViewById(R.id.howtomed2);
        String video3 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cyMxWXlX9sU?si=sMNHOdC83fxMb2GP\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView3.loadData(video3, "text/html", "utf-8");
        webView3.getSettings().setJavaScriptEnabled(true);
        webView3.setWebChromeClient(new WebChromeClient());

        WebView webView4 = findViewById(R.id.YogaDef);
        String video4 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/SCiLNtGO0jg?si=btMKg_4dNzlJLuug\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView4.loadData(video4, "text/html", "utf-8");
        webView4.getSettings().setJavaScriptEnabled(true);
        webView4.setWebChromeClient(new WebChromeClient());

        WebView webView5 = findViewById(R.id.YogaDef1);
        String video5 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/RI3PvPzhdyU?si=uQL7yd__MnwNmwa7\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView5.loadData(video5, "text/html", "utf-8");
        webView5.getSettings().setJavaScriptEnabled(true);
        webView5.setWebChromeClient(new WebChromeClient());

        WebView webView6 = findViewById(R.id.yogatut1);
        String video6 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/VzY6XuOSoHw?si=O7IcOPErdHDkgtCE\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView6.loadData(video6, "text/html", "utf-8");
        webView6.getSettings().setJavaScriptEnabled(true);
        webView6.setWebChromeClient(new WebChromeClient());

        WebView webView7 = findViewById(R.id.yogatut2);
        String video7 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/j7rKKpwdXNE?si=ltFR6UlPImk7-8aI\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView7.loadData(video7, "text/html", "utf-8");
        webView7.getSettings().setJavaScriptEnabled(true);
        webView7.setWebChromeClient(new WebChromeClient());

        WebView webView8 = findViewById(R.id.yogatut3);
        String video8 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/etSowWscnJk?si=5gmJawHrz4W7GNHA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView8.loadData(video8, "text/html", "utf-8");
        webView8.getSettings().setJavaScriptEnabled(true);
        webView8.setWebChromeClient(new WebChromeClient());
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