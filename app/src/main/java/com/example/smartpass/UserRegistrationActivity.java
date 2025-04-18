package com.example.smartpass;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistrationActivity extends AppCompatActivity {

    private EditText editUserName, editUserPhone, editUserAge, editUserEmail, editUserPassword, editUserPasswordAgain;
    private Button userSignUpBtn;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_registration);

        // Adjust system UI padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");

        // Initialize UI components
        editUserName = findViewById(R.id.editUserName);
        editUserPhone = findViewById(R.id.editUserPhone);
        editUserAge = findViewById(R.id.editUserAge);
        editUserEmail = findViewById(R.id.editUserEmail);
        editUserPassword = findViewById(R.id.editUserPassword);
        editUserPasswordAgain = findViewById(R.id.editUserPasswordAgain);
        userSignUpBtn = findViewById(R.id.UserSignUpBtn);

        // Set click listener for SignUp button
        userSignUpBtn.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String name = editUserName.getText().toString().trim();
        String phone = editUserPhone.getText().toString().trim();
        String ageStr = editUserAge.getText().toString().trim();
        String email = editUserEmail.getText().toString().trim();
        String password = editUserPassword.getText().toString().trim();
        String passwordAgain = editUserPasswordAgain.getText().toString().trim();

        // Validate user input
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(ageStr)
                || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordAgain)) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(passwordAgain)) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            Toast.makeText(this, "Invalid email format!", Toast.LENGTH_SHORT).show();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 18) {
                Toast.makeText(this, "You must be at least 18 years old to register.", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid age!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create User object
        User user = new User(name, phone, age, email, password);

        // Save to Firebase
        usersRef.push().setValue(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(UserRegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    clearFields();
                })
                .addOnFailureListener(e -> Toast.makeText(UserRegistrationActivity.this, "Failed to register! Try again.", Toast.LENGTH_SHORT).show());
    }

    private void clearFields() {
        editUserName.setText("");
        editUserPhone.setText("");
        editUserAge.setText("");
        editUserEmail.setText("");
        editUserPassword.setText("");
        editUserPasswordAgain.setText("");
    }
}
