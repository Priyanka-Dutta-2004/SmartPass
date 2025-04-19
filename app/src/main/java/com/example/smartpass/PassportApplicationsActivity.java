package com.example.smartpass;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;

public class PassportApplicationsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport_applications);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, AdminLoginActivity.class));
            finish();
            return;
        }

        Spinner statusFilter = findViewById(R.id.statusFilter);
        String[] statuses = {"All", "Pending", "Approved", "Rejected"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statuses);
        statusFilter.setAdapter(adapter);

        RecyclerView recyclerView = findViewById(R.id.applicationsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data (replace with Firebase or API call)
        List<Application> applications = new ArrayList<>();
        applications.add(new Application("1", "John Doe", "Pending"));
        applications.add(new Application("2", "Jane Smith", "Approved"));
        recyclerView.setAdapter(new ApplicationAdapter(applications));
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
            return true;
        } else if (id == R.id.menu_logout) {
            mAuth.signOut();
            startActivity(new Intent(this, AdminLoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}