package com.example.smartpass;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {
    private EditText adminEmail, adminPassword;
    private TextView errorText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mAuth = FirebaseAuth.getInstance();
        adminEmail = findViewById(R.id.adminEmail);
        adminPassword = findViewById(R.id.adminPassword);
        errorText = findViewById(R.id.errorText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String email = adminEmail.getText().toString().trim();
            String password = adminPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                errorText.setText("Please fill in all fields");
                errorText.setVisibility(TextView.VISIBLE);
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(this, AdminDashboardActivity.class));
                            finish();
                        } else {
                            errorText.setText("Login failed: " + task.getException().getMessage());
                            errorText.setVisibility(TextView.VISIBLE);
                        }
                    });
        });
    }
}