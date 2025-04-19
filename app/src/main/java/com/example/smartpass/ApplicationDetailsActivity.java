package com.example.smartpass;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ApplicationDetailsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_details);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, AdminLoginActivity.class));
            finish();
            return;
        }

        TextView applicantName = findViewById(R.id.applicantName);
        TextView applicationId = findViewById(R.id.applicationId);
        ImageView documentPreview = findViewById(R.id.documentPreview);
        Button approveButton = findViewById(R.id.approveButton);
        Button rejectButton = findViewById(R.id.rejectButton);
        EditText rejectReason = findViewById(R.id.rejectReason);

        // Get application ID from intent
        String appId = getIntent().getStringExtra("applicationId");
        // Sample data (replace with Firebase or API call)
        applicantName.setText("Name: John Doe");
        applicationId.setText("Application ID: " + appId);

        approveButton.setOnClickListener(v -> {
            // Update status to Approved in backend
            finish();
        });

        rejectButton.setOnClickListener(v -> {
            rejectReason.setVisibility(EditText.VISIBLE);
            rejectButton.setOnClickListener(v1 -> {
                String reason = rejectReason.getText().toString();
                if (!reason.isEmpty()) {
                    // Update status to Rejected with reason
                    finish();
                }
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_manage_users) {
            startActivity(new Intent(this, ManageAdminActivity.class));
        } else if (id == R.id.menu_view_passports) {
            startActivity(new Intent(this, PassportApplicationsActivity.class));
        } else if (id == R.id.menu_logout) {
            mAuth.signOut();
            startActivity(new Intent(this, AdminLoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}