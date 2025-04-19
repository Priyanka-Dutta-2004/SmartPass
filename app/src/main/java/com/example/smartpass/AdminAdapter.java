package com.example.smartpass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {
    private final List<Admin> admins;

    public AdminAdapter(List<Admin> admins) {
        this.admins = admins;
    }

    @Override
    public AdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin, parent, false);
        return new AdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdminViewHolder holder, int position) {
        Admin admin = admins.get(position);

        holder.adminIdText.setText("ID: " + admin.getId());
        holder.adminPasswordText.setText("Password: " + admin.getPassword());
        holder.adminEmailText.setText("Email: " + admin.getEmail());
        holder.adminStatusText.setText("Status: " + admin.getStatus());

        if (admin.getStatus().equals("Suspended")) {
            holder.suspendButton.setEnabled(false);
            holder.suspendButton.setText("Suspended");
        } else {
            holder.suspendButton.setOnClickListener(v -> {
                // Update status to Suspended in Firebase
                admin.setStatus("Suspended");
                notifyItemChanged(position);
                // Add Firebase update logic here
            });
        }
    }

    @Override
    public int getItemCount() {
        return admins.size();
    }

    static class AdminViewHolder extends RecyclerView.ViewHolder {
        TextView  adminIdText, adminPasswordText, adminEmailText, adminStatusText;
        Button suspendButton;

        public AdminViewHolder(View itemView) {
            super(itemView);

            adminIdText = itemView.findViewById(R.id.adminIdText);
            adminPasswordText = itemView.findViewById(R.id.adminPasswordText);
            adminEmailText = itemView.findViewById(R.id.adminEmailText);
            adminStatusText = itemView.findViewById(R.id.adminStatusText);
            suspendButton = itemView.findViewById(R.id.suspendButton);
        }
    }
}