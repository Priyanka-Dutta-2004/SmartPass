package com.example.smartpass;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.*;
import android.widget.*;
import java.util.List;

public class ApplicantAdapter extends ArrayAdapter<ApplicantPolice> {
    private final Activity context;
    private final List<ApplicantPolice> applicants;

    public ApplicantAdapter(Activity context, List<ApplicantPolice> applicants) {
        super(context, R.layout.item_applicant, applicants);
        this.context = context;
        this.applicants = applicants;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.item_applicant, null, true);

        TextView name = rowView.findViewById(R.id.tvName);
        TextView passport = rowView.findViewById(R.id.tvPassport);
        TextView address = rowView.findViewById(R.id.tvAddress);

        ApplicantPolice a = applicants.get(position);
        name.setText(a.getName());
        passport.setText("Passport ID: " + a.getPassportId());
        address.setText("Address: " + a.getAddress());

        return rowView;
    }
}
