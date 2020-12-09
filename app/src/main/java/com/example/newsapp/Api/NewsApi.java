package com.example.newsapp.Api;

import com.example.newsapp.Model.PostList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class NewsApi {
    private static final String key ="7b7391918b29417c83ead665a59d60de";
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String linkInt = "top-headlines?sources=techcrunch&apiKey=";
    private static final String linkInd = "top-headlines?country=in&apiKey=";;
    private static final String linkSport = "top-headlines?category=sports&apiKey=";
    private static final String linkBussiness = "top-headlines?category=business&apiKey=";

    public static PostService postService = null;
    public static PostService getService()
    {
        if(postService==null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService=retrofit.create(PostService.class);
        }
        return postService;

    }

    public interface PostService {
        @GET(linkInt + key)
        Call<PostList> getIntPostList();

        @GET(linkInd + key)
        Call<PostList> getIndPostList();

        @GET(linkSport + key)
        Call<PostList> getSportsPostList();
        @GET(linkBussiness + key)
        Call<PostList> getBusinessPostList();


        }

}
