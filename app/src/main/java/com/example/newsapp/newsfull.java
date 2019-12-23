package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class newsfull extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfull);
        getSupportActionBar().hide();

        String url=getIntent().getStringExtra("url");
        WebView mywebview = (WebView) findViewById(R.id.news);
        mywebview.loadUrl(url+"/");


    }
}
