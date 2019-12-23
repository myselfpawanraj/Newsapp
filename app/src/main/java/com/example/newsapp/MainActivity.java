package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
    public void changeInt(View view){
        Intent intent = new Intent(this, news.class);
        intent.putExtra("x",0);
        startActivity(intent);
    }
    public void changeInd(View view){
        Intent intent = new Intent(this, news.class);
        intent.putExtra("x",1);
        startActivity(intent);
    }
    public void changeSports(View view){
        Intent intent = new Intent(this, news.class);
        intent.putExtra("x",2);
        startActivity(intent);
    }
    public void changeBusiness(View view){
        Intent intent = new Intent(this, news.class);
        intent.putExtra("x",3);
        startActivity(intent);
    }
    public void changeMd(View view){
        Intent intent = new Intent(this, mdweb.class);
        startActivity(intent);
    }


}
