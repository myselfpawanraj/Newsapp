package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Article> articleList;
    private Context context;

    public MyAdapter(List<Article> listItems, Context context) {
        this.articleList = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article listItem = articleList.get(position);

        holder.textViewHead.setText(listItem.getTitle());
        holder.textViewDesc.setText(listItem.getDescription());
        Picasso.with(context)
                .load(listItem.getUrlToImage())
                .into(holder.img);
        holder.img.setOnClickListener(v -> {
            Toast.makeText(context, listItem.getContent(), Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(context,newsfull.class);
            intent.putExtra("url",listItem.getUrl());
            context.startActivity(intent);

        });

        holder.time.setText(listItem.getPublishedAt());
        holder.src.setText(listItem.getSource().getName());

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewHead= (TextView) itemView.findViewById(R.id.textViewHead);
        public TextView textViewDesc= (TextView) itemView.findViewById(R.id.textViewDesc);
        public TextView time = (TextView) itemView.findViewById(R.id.time);
        public TextView src = (TextView) itemView.findViewById(R.id.sourcename);
        public ImageView img = (ImageView) itemView.findViewById(R.id.imageView);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
