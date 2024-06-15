package com.example.nurasa.menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nurasa.R;
import com.example.nurasa.history.MenuModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends AppCompatActivity {

    public static final String DATA_TITLE = "data_title";

    private DatabaseReference mDatabase;

    private TextView tvPaket1, tvPaket2, tvPaket3, tvPaket4, tvPaket5, tvPaket6, tvTotalPrice, tvJumlahPorsi;
    private ImageView imageAdd1, imageAdd2, imageAdd3, imageAdd4, imageAdd5, imageAdd6;
    private ImageView imageMinus1, imageMinus2, imageMinus3, imageMinus4, imageMinus5, imageMinus6;
    private MaterialButton btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Aktifkan tombol back di toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setInitLayout();
    }

    private void setInitLayout() {
        tvPaket1 = findViewById(R.id.tvPaket1);
        tvPaket2 = findViewById(R.id.tvPaket2);
        tvPaket3 = findViewById(R.id.tvPaket3);
        tvPaket4 = findViewById(R.id.tvPaket4);
        tvPaket5 = findViewById(R.id.tvPaket5);
        tvPaket6 = findViewById(R.id.tvPaket6);

        imageAdd1 = findViewById(R.id.imageAdd1);
        imageAdd2 = findViewById(R.id.imageAdd2);
        imageAdd3 = findViewById(R.id.imageAdd3);
        imageAdd4 = findViewById(R.id.imageAdd4);
        imageAdd5 = findViewById(R.id.imageAdd5);
        imageAdd6 = findViewById(R.id.imageAdd6);

        imageMinus1 = findViewById(R.id.imageMinus1);
        imageMinus2 = findViewById(R.id.imageMinus2);
        imageMinus3 = findViewById(R.id.imageMinus3);
        imageMinus4 = findViewById(R.id.imageMinus4);
        imageMinus5 = findViewById(R.id.imageMinus5);
        imageMinus6 = findViewById(R.id.imageMinus6);

        tvJumlahPorsi = findViewById(R.id.tvJumlahPorsi);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOrder();
            }
        });

        setButtonClickListeners();
        updateUI();
    }

    private void setButtonClickListeners() {
        imageAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementItemCount(tvPaket1);
            }
        });
        imageAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementItemCount(tvPaket2);
            }
        });
        imageAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementItemCount(tvPaket3);
            }
        });
        imageAdd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementItemCount(tvPaket4);
            }
        });
        imageAdd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementItemCount(tvPaket5);
            }
        });
        imageAdd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementItemCount(tvPaket6);
            }
        });

        imageMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementItemCount(tvPaket1);
            }
        });
        imageMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementItemCount(tvPaket2);
            }
        });
        imageMinus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementItemCount(tvPaket3);
            }
        });
        imageMinus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementItemCount(tvPaket4);
            }
        });
        imageMinus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementItemCount(tvPaket5);
            }
        });
        imageMinus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementItemCount(tvPaket6);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Panggil onBackPressed() saat tombol back ditekan
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Tambahkan logika khusus jika diperlukan sebelum menutup activity
        super.onBackPressed(); // Panggil super untuk menutup activity
    }

    private int calculateTotalItems() {
        int totalItems = 0;
        totalItems += Integer.parseInt(tvPaket1.getText().toString());
        totalItems += Integer.parseInt(tvPaket2.getText().toString());
        totalItems += Integer.parseInt(tvPaket3.getText().toString());
        totalItems += Integer.parseInt(tvPaket4.getText().toString());
        totalItems += Integer.parseInt(tvPaket5.getText().toString());
        totalItems += Integer.parseInt(tvPaket6.getText().toString());
        return totalItems;
    }

    private int calculateTotalPrice() {
        int pricePerItem1 = 21000;
        int pricePerItem2 = 34000;
        int pricePerItem3 = 23700;
        int pricePerItem4 = 22500;
        int pricePerItem5 = 16500;
        int pricePerItem6 = 26000;

        int quantity1 = Integer.parseInt(tvPaket1.getText().toString());
        int quantity2 = Integer.parseInt(tvPaket2.getText().toString());
        int quantity3 = Integer.parseInt(tvPaket3.getText().toString());
        int quantity4 = Integer.parseInt(tvPaket4.getText().toString());
        int quantity5 = Integer.parseInt(tvPaket5.getText().toString());
        int quantity6 = Integer.parseInt(tvPaket6.getText().toString());

        int totalPrice = (pricePerItem1 * quantity1) +
                (pricePerItem2 * quantity2) +
                (pricePerItem3 * quantity3) +
                (pricePerItem4 * quantity4) +
                (pricePerItem5 * quantity5) +
                (pricePerItem6 * quantity6);

        return totalPrice;
    }

    private void incrementItemCount(TextView textView) {
        int count = Integer.parseInt(textView.getText().toString());
        count++;
        textView.setText(String.valueOf(count));
        updateUI();
    }

    private void decrementItemCount(TextView textView) {
        int count = Integer.parseInt(textView.getText().toString());
        if (count > 0) {
            count--;
            textView.setText(String.valueOf(count));
            updateUI();
        }
    }

    private void createOrder() {
        String title = getIntent().getStringExtra(DATA_TITLE);
        int totalItems = calculateTotalItems();
        int totalPrice = calculateTotalPrice();
        String tanggalPesananSelesai = FunctionHelper.getToday();

        MenuModel menuModel = new MenuModel(title, totalPrice, tanggalPesananSelesai);

        // Simpan data pesanan ke Firebase
        String orderId = mDatabase.child("orders").push().getKey();
        mDatabase.child("orders").child(orderId).setValue(menuModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(OrderActivity.this, "Pesanan berhasil dibuat!", Toast.LENGTH_SHORT).show();
                        finish(); // Selesai activity setelah pesanan berhasil dibuat
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(OrderActivity.this, "Gagal membuat pesanan: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI() {
        int totalItems = calculateTotalItems();
        int totalPrice = calculateTotalPrice();
        tvJumlahPorsi.setText(totalItems + " items");
        tvTotalPrice.setText(FunctionHelper.rupiahFormat(totalPrice));
    }
}
