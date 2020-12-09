package com.example.newsapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

import com.example.newsapp.Activity.LoginActivity;
import com.example.newsapp.Adapter.TopHeadlineAdapter;
import com.example.newsapp.Adapter.VerticalAdapter;
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

    private PostList list;
    private List<List<Article>> verticalList;
    private RecyclerView recyclerView, recyclerViewHeadline;
    private SwipeRefreshLayout refreshLayout;
    private TextView textView;
    private ImageView logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= v.findViewById( R.id.recycler_vertical);
        recyclerViewHeadline=v.findViewById( R.id.recycler_top_news );
        refreshLayout=v.findViewById( R.id.refresh );
        textView=v.findViewById( R.id.textView2 );
        logout=v.findViewById( R.id.logout );
        verticalList = new ArrayList<>();

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
                verticalList.clear();
                getData(0);
                Toast.makeText(getContext(), "Fresh news loaded!", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        } );

        getData(0);
        setUser();

        return v;
    }

    private void getData(int id) {
        Call<PostList> postList;

        if(id==0) postList = NewsApi.getService().getIntPostList();
        else if(id==1) postList = NewsApi.getService().getIndPostList();
        else if(id==2) postList = NewsApi.getService().getSportsPostList();
        else if(id==3) postList = NewsApi.getService().getBusinessPostList();
        else{
            setData();
            return;
        }

        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                if(!response.isSuccessful()){
                    getData(id);
                    return;
                }
                list = response.body();
                verticalList.add(list.getArticles());
                Log.i("countTotal",verticalList.size()+"");
                getData(id+1);
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                getData(id);
            }
        });
    }
    private void setData(){
        VerticalAdapter adapter =new VerticalAdapter(verticalList,getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        TopHeadlineAdapter topHeadlineAdapter =new TopHeadlineAdapter(verticalList.get(1),getContext());
        recyclerViewHeadline.setHasFixedSize(true);
        recyclerViewHeadline.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHeadline.setAdapter(topHeadlineAdapter);
    }

    private void setUser(){
        SharedPreferences sharedPref = getActivity().getSharedPreferences("A", Context.MODE_PRIVATE);
        textView.setText(sharedPref.getString("name", null)+ "'s briefing");
    }
}
