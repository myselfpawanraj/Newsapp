
package com.example.newsapp.Adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Model.Article;
import com.example.newsapp.R;

import java.util.List;
public class VerticalAdapter extends RecyclerView.Adapter< VerticalAdapter.ViewHolder> {
    private List<List<Article>> newsList;
    private Context context;

    public VerticalAdapter(List<List<Article>> listItems, Context context) {
        this.newsList = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.card_vertical,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position==0)
            holder.textView.setText("International News");
        else if(position==1)
            holder.textView.setText("Indian News");
        else if(position==2)
            holder.textView.setText("Sports News");
        else if(position==3)
            holder.textView.setText("Business News");

        final List<Article> listItem = newsList.get(position);
        HorizontalAdapter adapter =new HorizontalAdapter(listItem,context);
        holder.verticalItem.setHasFixedSize(true);
        holder.verticalItem.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.verticalItem.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        Log.i("getItemCount",newsList.size()+"");
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView verticalItem= (RecyclerView) itemView.findViewById(R.id.verticleitem);
        public TextView textView= itemView.findViewById(R.id.textview_news_type);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
