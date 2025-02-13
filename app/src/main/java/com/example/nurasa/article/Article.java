package com.example.nurasa.article;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nurasa.R;

public class Article extends AppCompatActivity {

    private ImageView backButton;
    private ImageView logo;
    private TextView articleTitle;
    private TextView article1Title;
    private ImageView article1Image;
    private TextView article1Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);

        // Initialize views
        backButton = findViewById(R.id.back_button);
        logo = findViewById(R.id.logo);
        articleTitle = findViewById(R.id.article_title);
        article1Title = findViewById(R.id.article1_title);
        article1Image = findViewById(R.id.article1_image);
        article1Content = findViewById(R.id.article1_content);

        // Set up back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

    }

    // Method to handle back button click
    private void goBack() {
        finish(); // Close the current activity
    }
}
