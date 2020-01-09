
package com.example.newsapp.Adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Model.Article;
import com.example.newsapp.R;

import java.util.List;
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<List<Article>> newsList;
    private Context context;

    public ListAdapter(List<List<Article>> listItems, Context context) {
        this.newsList = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.verticleitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final List<Article> listItem = newsList.get(position);
        MyAdapter adapter =new MyAdapter(listItem,context);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
