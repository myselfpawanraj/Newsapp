package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class news extends AppCompatActivity {
    public PostList list;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public List<List<Article>> HorizontalListItems=null ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        for(int i=0;i<=3;i++){
        getData(i);}

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new verticalAdapter( HorizontalListItems, news.this );
        recyclerView.setAdapter( adapter );




    }


    private void getData(int i)
    {
        Call<PostList> postList;
        switch (i){
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
                HorizontalListItems.add( list.getArticles() );

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

            }
        });
    }

}
