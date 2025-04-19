package com.example.smartpass;

public class Application {
    private final String id;
    private final String applicantName;
    private final String status;

    public Application(String id, String applicantName, String status) {
        this.id = id;
        this.applicantName = applicantName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getStatus() {
        return status;
    }
}