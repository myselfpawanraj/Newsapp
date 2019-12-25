package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class news extends AppCompatActivity {
    public PostList list;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();


    }


    private void getData()
    {
        Call<PostList> postList;
        int x = getIntent().getIntExtra("x",0);
        switch (x){
            case 0: postList= newsApi.getService().getIntPostList();
                break;
            case 1: postList= newsApi.getService().getIndPostList();
                break;
            case 2: postList= newsApi.getService().getSportsPostList();
                break;
            case 3: postList= newsApi.getService().getBusinessPostList();
                break;
            default:
                postList= newsApi.getService().getIntPostList();
        }
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                list = response.body();
                adapter = new MyAdapter(list.getArticles(), news.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

            }
        });
    }
    public void showfull(){
        Intent intent = new Intent(news.this, newsfull.class);
        startActivity(intent);
    }

}
