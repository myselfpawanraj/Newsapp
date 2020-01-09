package com.example.newsapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsapp.Activity.HomeActivity;
import com.example.newsapp.Activity.LoginActivity;
import com.example.newsapp.Adapter.ListAdapter;
import com.example.newsapp.Adapter.MyAdapter;
import com.example.newsapp.Api.NewsApi;
import com.example.newsapp.Model.Article;
import com.example.newsapp.Model.PostList;
import com.example.newsapp.Model.User;
import com.example.newsapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragNews extends Fragment {

    private PostList list;
    private List<List<Article>> verticalList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TextView textView2;
    private ImageView logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= v.findViewById( R.id.vertirecy );
        refreshLayout=v.findViewById( R.id.refresh );
        textView2=v.findViewById( R.id.textView2 );
        logout=v.findViewById( R.id.logout );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getActivity().getSharedPreferences("CheckLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("login", "false");
                editor.apply();
                Toast.makeText(getActivity(), "Logged out!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        } );
        refreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startActivity( getActivity().getIntent() );
            }
        } );
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        verticalList = new ArrayList<>();
        getData();
        setUser();

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
                Log.i("countTotal",verticalList.size()+"");
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
                Log.i("countTotal",verticalList.size()+"");
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
                Log.i("countTotal",verticalList.size()+"");
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
                Log.i("countTotal",verticalList.size()+"");
                ListAdapter adapter = new ListAdapter( verticalList, getContext() );
                recyclerView.setAdapter( adapter );

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

            }
        });
    }

    private void setUser()
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("A", Context.MODE_PRIVATE);
        textView2.setText(sharedPref.getString("name", null)+ "'s briefing");
    }
}
