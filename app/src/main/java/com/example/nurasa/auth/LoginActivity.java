package com.example.nurasa.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.nurasa.R;
import com.example.nurasa.homepage.Homepage;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    MaterialButton btnLogin, btnRegister;
    TextInputEditText inputEmail, inputPassword;
    FirebaseAuth firebaseAuth;
    String strEmail, strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FirebaseApp.initializeApp(this);

        setInitLayout();
        setInputData();
    }

    private void setInitLayout() {
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void setInputData() {
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            strEmail = inputEmail.getText().toString().trim();
            strPassword = inputPassword.getText().toString().trim();

            if (strEmail.isEmpty() || strPassword.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Ups, Form harus diisi semua!", Toast.LENGTH_LONG).show();
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
                Toast.makeText(LoginActivity.this, "Email tidak valid!", Toast.LENGTH_LONG).show();
            } else {
                Log.d(TAG, "Attempting to login user with email: " + strEmail);
                loginUser(strEmail, strPassword);
            }
        });
    }

    private void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            Log.d(TAG, "Login successful: " + user.getEmail());
                            Intent intent = new Intent(LoginActivity.this, Homepage.class);
                            startActivity(intent);
                        } else {
                            Log.e(TAG, "User is null after successful login");
                            Toast.makeText(LoginActivity.this, "User tidak ditemukan setelah login berhasil", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.e(TAG, "Login failed: ", task.getException());
                        Toast.makeText(LoginActivity.this, "Ups, Email atau Password Anda salah!", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Login failure: ", e);
                    Toast.makeText(LoginActivity.this, "Login gagal: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}
