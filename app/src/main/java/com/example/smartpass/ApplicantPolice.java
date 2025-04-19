package com.example.smartpass;


public class ApplicantPolice {
    private int id;
    private String name;
    private String passportId;
    private String address;
    private String status; // "Pending", "Verified", "Rejected"

    public ApplicantPolice(int id, String name, String passportId, String address, String status) {
        this.id = id;
        this.name = name;
        this.passportId = passportId;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassportId() {
        return passportId;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }
}
