package com.example.smartpass;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ManageAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_admin);

        AdminDatabaseHelper dbHelper = new AdminDatabaseHelper(this);

        RecyclerView recyclerView = findViewById(R.id.usersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Admin> admins = dbHelper.getAllAdmins();
        recyclerView.setAdapter(new AdminAdapter(admins));
    }
}