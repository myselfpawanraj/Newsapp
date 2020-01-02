package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class verticalAdapter extends RecyclerView.Adapter<verticalAdapter.ViewHolder> {
    private List<List<Article>> list;
    private Context context;

    public verticalAdapter(List<List<Article>> list, Context context) {
        this.list = list;
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
        List<Article> articleList = list.get(position);
        RecyclerView.Adapter adapter = new horizontalAdapter(articleList, this.context );
        holder.horizontalContainer.setAdapter( adapter );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView horizontalContainer= (RecyclerView) itemView.findViewById(R.id.horizontalcontainer);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
