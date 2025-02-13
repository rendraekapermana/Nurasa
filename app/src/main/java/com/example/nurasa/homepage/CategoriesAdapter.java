package com.example.nurasa.homepage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nurasa.R;
import com.example.nurasa.menu.OrderActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<ModelCategories> modelCategoriesList;
    private Context context;

    public CategoriesAdapter(Context context, List<ModelCategories> items) {
        this.context = context;
        this.modelCategoriesList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelCategories data = modelCategoriesList.get(position);

        // Load image from URL using Picasso
        Picasso.get().load(data.getImageResource()).into(holder.imageIcon);

        // Set category name
        holder.tvName.setText(data.getName());

        // Set onClickListener to navigate to OrderActivity
        holder.cvCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderActivity.class);
                intent.putExtra(OrderActivity.DATA_TITLE, data.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelCategoriesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cvCategories;
        public TextView tvName;
        public ImageView imageIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            cvCategories = itemView.findViewById(R.id.cvCategories);
            tvName = itemView.findViewById(R.id.tvName);
            imageIcon = itemView.findViewById(R.id.imageIcon);
        }
    }
}
