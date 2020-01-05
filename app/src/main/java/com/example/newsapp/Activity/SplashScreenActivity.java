package com.example.newsapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.newsapp.R;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(3000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(i);
            SplashScreenActivity.this.finish();
        }

    }
}
