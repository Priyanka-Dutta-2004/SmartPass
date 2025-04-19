package com.example.smartpass;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ApplicantDetailActivity extends AppCompatActivity {
    TextView tvDetails;
    Button btnVerify, btnReject;
    DatabaseHelper dbHelper;
    ApplicantPolice applicant;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_detail);

        int applicantId = getIntent().getIntExtra("id", -1);
        dbHelper = new DatabaseHelper(this);
        for (ApplicantPolice a : dbHelper.getAllApplicants()) {
            if (a.getId() == applicantId) {
                applicant = a;
                break;
            }
        }

        tvDetails = findViewById(R.id.tvDetails);
        btnVerify = findViewById(R.id.btnVerify);
        btnReject = findViewById(R.id.btnReject);

        if (applicant != null) {
            tvDetails.setText("Name: " + applicant.getName() +
                    "\nPassport ID: " + applicant.getPassportId() +
                    "\nAddress: " + applicant.getAddress() +
                    "\nStatus: " + applicant.getStatus());

            btnVerify.setOnClickListener(v -> {
                dbHelper.updateStatus(applicant.getId(), "Verified");
                finish();
            });

            btnReject.setOnClickListener(v -> {
                dbHelper.updateStatus(applicant.getId(), "Rejected");
                finish();
            });
        }
    }
}
