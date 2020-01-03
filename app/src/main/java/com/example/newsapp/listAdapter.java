
package com.example.newsapp;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import java.util.List;
public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {
    private List<List<Article>> articleList;
    private Context context;

    public listAdapter(List<List<Article>> listItems, Context context) {
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

        final List<Article> listItem = articleList.get(position);
        MyAdapter adapter =new MyAdapter(listItem,context);
        holder.verticalItem.setAdapter(adapter);
        holder.verticalItem.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.verticalItem.setLayoutManager(layoutManager);

    }

    @Override
    public int getItemCount() {
        Log.i("counting",articleList.size()+"");
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView verticalItem= (RecyclerView) itemView.findViewById(R.id.verticleitem);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
