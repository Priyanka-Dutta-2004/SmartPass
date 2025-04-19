package com.example.smartpass;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder> {
    private final List<Application> applications;

    public ApplicationAdapter(List<Application> applications) {
        this.applications = applications;
    }

    @Override
    public ApplicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_application, parent, false);
        return new ApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ApplicationViewHolder holder, int position) {
        Application application = applications.get(position);
        holder.applicantNameText.setText(application.getApplicantName());
        holder.applicationIdText.setText("ID: " + application.getId());
        holder.statusText.setText("Status: " + application.getStatus());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ApplicationDetailsActivity.class);
            intent.putExtra("applicationId", application.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    static class ApplicationViewHolder extends RecyclerView.ViewHolder {
        TextView applicantNameText, applicationIdText, statusText;

        public ApplicationViewHolder(View itemView) {
            super(itemView);
            applicantNameText = itemView.findViewById(R.id.applicantNameText);
            applicationIdText = itemView.findViewById(R.id.applicationIdText);
            statusText = itemView.findViewById(R.id.statusText);
        }
    }
}