package com.example.smartpass;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Button manageUsersButton = findViewById(R.id.manageUsersButton);
        Button viewApplicationsButton = findViewById(R.id.viewApplicationsButton);
        Button viewLogsButton = findViewById(R.id.viewLogsButton);

        manageUsersButton.setOnClickListener(v -> startActivity(new Intent(this, ManageAdminActivity.class)));
        viewApplicationsButton.setOnClickListener(v -> startActivity(new Intent(this, PassportApplicationsActivity.class)));
        viewLogsButton.setOnClickListener(v -> {
            // Add AuditLogsActivity later
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
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, AdminLoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}