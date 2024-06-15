package com.example.nurasa.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.nurasa.R;
import com.example.nurasa.homepage.Homepage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class Profile extends AppCompatActivity {

    private static final String TAG = "ProfilePage";
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView profileImage,ic_back;
    private Uri imageUri;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private static final String PROFILE_IMAGE_PATH = "profile_images/profile_image.jpg"; // Fixed path for profile image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        profileImage = findViewById(R.id.profile_image);
        Button buttonEditProfile = findViewById(R.id.button_edit_profile);
        Button buttonChangePicture = findViewById(R.id.button_change_picture);

        ic_back = findViewById(R.id.icon);
        ic_back.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, Homepage.class);
            startActivity(intent);
        });

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        buttonEditProfile.setOnClickListener(v -> openFileChooser());
        buttonChangePicture.setOnClickListener(v -> uploadFile());

        loadProfileImage();
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Error converting image to bitmap: " + e.getMessage());
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadFile() {
        if (imageUri != null) {
            StorageReference fileReference = storageReference.child(PROFILE_IMAGE_PATH);
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        Toast.makeText(Profile.this, "Upload successful", Toast.LENGTH_LONG).show();
                        saveProfileImageUrl(uri.toString()); // Simpan URL gambar profil
                        loadProfileImage(); // Reload the profile image
                    }))
                    .addOnFailureListener(e -> {
                        Log.e(TAG, "Upload failed: " + e.getMessage());
                        Toast.makeText(Profile.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveProfileImageUrl(String url) {
        SharedPreferences prefs = getSharedPreferences("profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("profile_image_url", url);
        editor.apply();
    }

    private void loadProfileImage() {
        SharedPreferences prefs = getSharedPreferences("profile", MODE_PRIVATE);
        String profileImageUrl = prefs.getString("profile_image_url", null);

        if (profileImageUrl != null) {
            Glide.with(this)
                    .load(profileImageUrl)
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .transform(new CircleCrop())  // Transformasi bulat
                    .into(profileImage);
        }
    }
}
