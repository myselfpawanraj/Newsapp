
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
    private List<List<Article>> articleList;
    private Context context;

    public ListAdapter(List<List<Article>> listItems, Context context) {
        this.articleList = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final List<Article> listItem = articleList.get(position);
        MyAdapter adapter =new MyAdapter(listItem,context);
        holder.verticalItem.setAdapter(adapter);
        holder.verticalItem.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.verticalItem.setLayoutManager(layoutManager);

    }

    @Override
    public int getItemCount() {
        Log.i("getItemCount",articleList.size()+"");
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView verticalItem= itemView.findViewById(R.id.verticleitem);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
