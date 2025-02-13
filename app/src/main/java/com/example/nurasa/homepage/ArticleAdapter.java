package com.example.nurasa.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nurasa.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<ModelArticle> modelArticleList;
    private Context ctx;
    private OnItemClickListener onItemClickListener; // ✅ Tambahkan variabel listener

    public ArticleAdapter(Context context, List<ModelArticle> items) {
        this.ctx = context;
        this.modelArticleList = items;
    }

    // ✅ Buat interface untuk menangani klik item
    public interface OnItemClickListener {
        void onItemClick(ModelArticle article);
    }

    // ✅ Tambahkan metode setter untuk listener klik
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
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

        // ✅ Pastikan onClickListener hanya dipanggil jika listener sudah di-set
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArticleList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cvArticle;
        public TextView tvPlaceName, tvVote;
        public ImageView imgThumb;

        public ViewHolder(View itemView) {
            super(itemView);
            cvArticle = itemView.findViewById(R.id.cvArticle);
            tvPlaceName = itemView.findViewById(R.id.tvPlaceName);
            tvVote = itemView.findViewById(R.id.tvVote);
            imgThumb = itemView.findViewById(R.id.imgThumb);
        }
    }
}
