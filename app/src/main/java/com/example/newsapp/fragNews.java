package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragNews extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        ImageView international = (ImageView) v.findViewById(R.id.imageButton6);
        ImageView india = (ImageView) v.findViewById(R.id.imageButton7);
        ImageView business = (ImageView) v.findViewById(R.id.imageButton9);
        ImageView sports = (ImageView) v.findViewById(R.id.imageButton10);
        TextView username = (TextView) v.findViewById(R.id.username);


        //to show username in menu
        String user=null;
        username.setText(user);


        international.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), news.class);
                intent.putExtra("x", 0);
                startActivity(intent);
            }
        });
        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), news.class);
                intent.putExtra("x",1);
                startActivity(intent);
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), news.class);
                intent.putExtra("x",2);
                startActivity(intent);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), news.class);
                intent.putExtra("x",3);
                startActivity(intent);
            }
        });


        return v;
    }
}
