package com.example.nurasa.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nurasa.R;
import com.example.nurasa.homepage.Homepage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderHistory extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<MenuModel, MenuAdapter.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.mRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firebase Database and Reference
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("orders");

        // Setup FirebaseRecyclerOptions with query and MenuModel class
        FirebaseRecyclerOptions<MenuModel> options =
                new FirebaseRecyclerOptions.Builder<MenuModel>()
                        .setQuery(databaseReference, MenuModel.class)
                        .build();

        // Initialize MenuAdapter with options and context
        adapter = new MenuAdapter(options, this);
        recyclerView.setAdapter(adapter);

        // Initialize the back button and set an OnClickListener
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderHistory.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
