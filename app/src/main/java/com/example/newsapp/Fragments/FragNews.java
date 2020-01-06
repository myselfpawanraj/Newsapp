package com.example.newsapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class FragNews extends Fragment {

    public PostList list;
    public List<List<Article>> verticalList = new ArrayList<>();
    ListAdapter adapter;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=(RecyclerView) v.findViewById( R.id.vertirecy );
        TextView username = (TextView) v.findViewById(R.id.username);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getData();
        return v;
    }



    private void getData() {
        Call<PostList> postList;
        postList = NewsApi.getService().getIntPostList();
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
        adapter = new ListAdapter(verticalList, getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
