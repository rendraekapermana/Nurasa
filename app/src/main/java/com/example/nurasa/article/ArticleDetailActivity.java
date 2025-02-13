package com.example.nurasa.article;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nurasa.R;
import com.squareup.picasso.Picasso;

public class ArticleDetailActivity extends AppCompatActivity {

    private ImageView imgArticle, backButton, logo;
    private TextView tvTitle, tvArticleTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);

        // Inisialisasi View sesuai dengan article.xml
        backButton = findViewById(R.id.back_button);
        logo = findViewById(R.id.logo);
        tvTitle = findViewById(R.id.article_title);
        tvArticleTitle = findViewById(R.id.article_title);
        imgArticle = findViewById(R.id.article1_image);


        // Ambil data dari Intent
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("article_title");
            String articleTitle = intent.getStringExtra("article1_title");
            String content = intent.getStringExtra("article_content");
            String imageUrl = intent.getStringExtra("article_image");

            Log.d("ArticleDetailActivity", "Title: " + title);
            Log.d("ArticleDetailActivity", "Article Title: " + articleTitle);
            Log.d("ArticleDetailActivity", "Content: " + content);
            Log.d("ArticleDetailActivity", "Image URL: " + imageUrl);

            // Set data ke View
            if (title != null) {
                tvTitle.setText(title);
            }
            if (articleTitle != null) {
                tvArticleTitle.setText(articleTitle);
            } else if (title != null) {
                tvArticleTitle.setText(title); // Fallback jika articleTitle tidak ada
            }
//            if (content != null && !content.isEmpty()) {
//                tvContent.setText(content);
//            } else {
//                tvContent.setText("Konten tidak tersedia");
//            }
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Picasso.get().load(imageUrl).into(imgArticle);
            } else {
                imgArticle.setImageResource(R.drawable.rendang);
            }
        }

        // Tambahkan event untuk tombol kembali
        backButton.setOnClickListener(v -> finish());
    }
}
