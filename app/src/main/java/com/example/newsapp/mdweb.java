package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class mdweb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdweb);
        getSupportActionBar().hide();
        WebView mywebview = (WebView) findViewById(R.id.fb);
        mywebview.loadUrl("https://mbasic.facebook.com/MDiitism/");

    }
}
