package com.example.nurasa.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.nurasa.R;
import com.example.nurasa.article.Article;
import com.example.nurasa.article.ArticleDetailActivity;
import com.example.nurasa.history.OrderHistory;
import com.example.nurasa.menu.OrderActivity;
import com.example.nurasa.profile.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {

    private List<ModelCategories> modelCategoriesList = new ArrayList<>();
    private List<ModelArticle> modelTrendingList = new ArrayList<>();
    private CategoriesAdapter categoriesAdapter;
    private ArticleAdapter articleAdapter;
    private RecyclerView rvCategories, rvTrending;
    private CardView cvHistory;
    private ImageView ivProfile;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        setInitLayout();
        fetchCategoriesFromFirebase();
        fetchArticleFromFirebase();
        loadProfileImage();
    }

    private void setInitLayout() {
        // Handle click event to navigate to "Order History" page
        cvHistory = findViewById(R.id.cvHistory);
        cvHistory.setOnClickListener(v -> {
            Intent intent = new Intent(Homepage.this, OrderHistory.class);
            startActivity(intent);
        });

        // Handle click event to navigate to "Order Activity" page
        rvCategories = findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new GridLayoutManager(this, 3));
        rvCategories.setHasFixedSize(true);

        // Handle click event to navigate to Profile page
        ivProfile = findViewById(R.id.Profile);
        ivProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Homepage.this, Profile.class);
            startActivity(intent);
        });

        // Setup RecyclerView for trending articles
        rvTrending = findViewById(R.id.rvArticle);
        rvTrending.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvTrending.setHasFixedSize(true);
    }

    private void loadProfileImage() {
        SharedPreferences prefs = getSharedPreferences("profile", MODE_PRIVATE);
        String profileImageUrl = prefs.getString("profile_image_url", null);

        if (profileImageUrl != null) {
            Glide.with(this)
                    .load(profileImageUrl)
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .transform(new CircleCrop())
                    .into(ivProfile);
        }
    }

    private void fetchCategoriesFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("categories");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelCategoriesList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ModelCategories modelCategories = postSnapshot.getValue(ModelCategories.class);
                    modelCategoriesList.add(modelCategories);
                }
                categoriesAdapter = new CategoriesAdapter(Homepage.this, modelCategoriesList);
                rvCategories.setAdapter(categoriesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Homepage.this, "Failed to fetch categories: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchArticleFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("article");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelTrendingList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ModelArticle modelArticle = postSnapshot.getValue(ModelArticle.class);
                    modelTrendingList.add(modelArticle);
                }
                articleAdapter = new ArticleAdapter(Homepage.this, modelTrendingList);
                articleAdapter.setOnItemClickListener(article -> {
                    Intent intent = new Intent(Homepage.this, ArticleDetailActivity.class);
                    intent.putExtra("article_title", article.getTvPlaceName());
                    intent.putExtra("article_content", article.getTvVote());
                    intent.putExtra("article_image", article.getImgThumb());
                    startActivity(intent);
                });
                rvTrending.setAdapter(articleAdapter);
                rvTrending.setAdapter(articleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Homepage.this, "Failed to fetch trending articles: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
