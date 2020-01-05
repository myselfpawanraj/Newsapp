package com.example.newsapp.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Adapter.ListAdapter;
import com.example.newsapp.Api.NewsApi;
import com.example.newsapp.Model.Article;
import com.example.newsapp.Model.PostList;
import com.example.newsapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    public PostList list;
    public List<List<Article>> verticalList = new ArrayList<>();
    ListAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();
        Log.i("respanse",verticalList.size()+"");

    }

    private void getData() {
        Call<PostList> postList;
        postList = NewsApi.getService().getIntPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                list = response.body();
                verticalList.add(list.getArticles());
                Log.i("respanse", list.getArticles().toString());
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

                Log.i("respanse", t.getMessage());
            }
        });
        postList = NewsApi.getService().getIndPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                list = response.body();
                verticalList.add(list.getArticles());

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

            }
        });
        postList = NewsApi.getService().getSportsPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                list = response.body();
                verticalList.add(list.getArticles());

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

            }
        });
        postList = NewsApi.getService().getBusinessPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                list = response.body();
                verticalList.add(list.getArticles());

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

            }
        });
        adapter = new ListAdapter(verticalList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
