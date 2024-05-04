package com.example.mentalhelp.MenuScreen.HappyFit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.mentalhelp.R;

public class HappyFit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_fit);

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


    }
}