package com.example.smartpass;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PoliceDashboardActivity extends AppCompatActivity {
    ListView lvApplicants;
    DatabaseHelper dbHelper;
    List<ApplicantPolice> applicantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_dashboard);

        lvApplicants = findViewById(R.id.lvApplicants);
        dbHelper = new DatabaseHelper(this);
        applicantList = dbHelper.getAllApplicants();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getDisplayList(applicantList));
        lvApplicants.setAdapter(adapter);

        lvApplicants.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, ApplicantDetailActivity.class);
            intent.putExtra("id", applicantList.get(position).getId());
            startActivity(intent);
        });
    }

    private List<String> getDisplayList(List<ApplicantPolice> list) {
        List<String> display = new ArrayList<>();
        for (ApplicantPolice a : list) {
            display.add(a.getName() + "\nPassport: " + a.getPassportId() + "\nStatus: " + a.getStatus());
        }
        return display;
    }

    @Override
    protected void onResume() {
        super.onResume();
        applicantList = dbHelper.getAllApplicants();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getDisplayList(applicantList));
        lvApplicants.setAdapter(adapter);
    }
}
