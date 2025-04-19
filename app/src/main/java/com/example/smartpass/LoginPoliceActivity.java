package com.example.smartpass;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPoliceActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    usernameEditText.setError("Username required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Password required");
                    return;
                }

                // Hardcoded login check for example
                if (username.equals("Police") && password.equals("1234")) {
                    Toast.makeText(LoginPoliceActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    // Go to the Police Dashboard or next screen
                    Intent intent = new Intent(LoginPoliceActivity.this, PoliceDashboardActivity.class);
                    startActivity(intent);
                    finish(); // Optional, so login activity doesn't stay in back stack
                } else {
                    Toast.makeText(LoginPoliceActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
