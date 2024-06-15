package com.example.nurasa.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nurasa.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<ModelArticle> modelArticleList;
    private Context ctx;
    private ArticleClickListener articleClickListener;

    public ArticleAdapter(Context context, List<ModelArticle> items, ArticleClickListener clickListener) {
        this.ctx = context;
        this.modelArticleList = items;
        this.articleClickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelArticle data = modelArticleList.get(position);

        // Load image from URL using Picasso
        Picasso.get().load(data.getImgThumb()).into(holder.imgThumb);
        holder.tvPlaceName.setText(data.getTvPlaceName());
        holder.tvVote.setText(data.getTvVote());

        // Set an OnClickListener to navigate to ArticleActivity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleClickListener.onArticleClick(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArticleList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumb;
        TextView tvPlaceName, tvVote;

        ViewHolder(View itemView) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.imgThumb);
            tvPlaceName = itemView.findViewById(R.id.tvPlaceName);
            tvVote = itemView.findViewById(R.id.tvVote);
        }
    }

    // Interface to handle click events on RecyclerView items
    public interface ArticleClickListener {
        void onArticleClick(ModelArticle article);
    }
}
