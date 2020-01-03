package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class news extends AppCompatActivity {
    public PostList list;
    public List<List<Article>> verticalList;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news );
        verticalList = new ArrayList<>();

        recyclerView = findViewById( R.id.recyclerView );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        getData();


    }

    private void getData() {
        Call<PostList> postList;
        postList = newsApi.getService().getIntPostList();
        postList.enqueue( new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                list = response.body();
                verticalList.add( list.getArticles() );
                Log.i( "respanse", response.body().toString() );
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

                Log.i( "respanse", t.getMessage() );
            }
        } );
        postList = newsApi.getService().getIndPostList();
        postList.enqueue( new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                list = response.body();
                verticalList.add( list.getArticles() );

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

            }
        } );
        postList = newsApi.getService().getSportsPostList();
        postList.enqueue( new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                list = response.body();
                verticalList.add( list.getArticles() );

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

            }
        } );
        postList = newsApi.getService().getBusinessPostList();
        postList.enqueue( new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                list = response.body();
                verticalList.add( list.getArticles() );

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

            }
        } );
        listAdapter adapter = new listAdapter( verticalList, this );
        recyclerView.setAdapter( adapter );
        adapter.notifyDataSetChanged();
    }
}
