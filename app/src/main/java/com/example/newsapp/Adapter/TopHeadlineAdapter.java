package com.example.newsapp.Adapter;

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

import com.example.newsapp.Activity.NewsfullActivity;
import com.example.newsapp.Model.Article;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopHeadlineAdapter extends RecyclerView.Adapter< TopHeadlineAdapter.ViewHolder> {
    private List<Article> articleList;
    private Context context;

    public TopHeadlineAdapter(List<Article> listItems, Context context) {
        this.articleList = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.card_home, parent, false );
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Article listItem = articleList.get( position );

        holder.textViewHead.setText( listItem.getTitle() );
        // holder.textViewDesc.setText( listItem.getDescription() );
        Picasso.get()
                .load( listItem.getUrlToImage() )
                .into( holder.img );
        holder.listView.setOnClickListener( v -> {
            Toast.makeText( context, listItem.getContent(), Toast.LENGTH_SHORT ).show();
            Intent intent = new Intent( context, NewsfullActivity.class );
            intent.putExtra( "url", listItem.getUrl() );
            context.startActivity( intent );

        } );

        holder.time.setText( listItem.getPublishedAt().substring( 0,10 )+" "+listItem.getPublishedAt().substring( 11,19 ) );
        holder.src.setText( listItem.getSource().getName() );

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHead = (TextView) itemView.findViewById( R.id.textViewHead );
        // public TextView textViewDesc = (TextView) itemView.findViewById( R.id.textViewDesc );
        public TextView time = (TextView) itemView.findViewById( R.id.time );
        public TextView src = (TextView) itemView.findViewById( R.id.sourcename );
        public ImageView img = (ImageView) itemView.findViewById( R.id.imageView );
        public CardView listView = (CardView) itemView.findViewById( R.id.card );
        ;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
        }
    }

}
