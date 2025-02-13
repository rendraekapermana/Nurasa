package com.example.nurasa.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nurasa.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MenuAdapter extends FirebaseRecyclerAdapter<MenuModel, MenuAdapter.ViewHolder> {

    private final Context context;

    public MenuAdapter(@NonNull FirebaseRecyclerOptions<MenuModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MenuModel model) {
        holder.tvMenu.setText(model.getNamaPaket()); // Set nama paket makanan
        holder.tvHarga.setText(String.valueOf(model.getHargaMakanan())); // Set harga makanan
        holder.tvTanggal.setText(model.getTanggalPesananSelesai()); // Set tanggal pesanan selesai
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_view_row, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMenu, tvHarga, tvTanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMenu = itemView.findViewById(R.id.tvMenu); // Sesuaikan dengan id yang benar di recycle_view_row.xml
            tvHarga = itemView.findViewById(R.id.textView5); // Sesuaikan dengan id yang benar di recycle_view_row.xml
            tvTanggal = itemView.findViewById(R.id.textView4); // Sesuaikan dengan id yang benar di recycle_view_row.xml
        }
    }
}
