package com.example.smartpass;

public class Admin {
    private final String id;
    private final String password;
    private final String Status;
    private final String email;

        public Admin(String id, int password, String Status, String email) {
            this.id = id;
            this.password = String.valueOf(password);
            this.Status = Status;
            this.email = email;

        }

        public String getId() {
            return id;
        }

       public String getPassword() {
            return password;
        }

        public String getStatus() {
        return Status;
        }

       public String getEmail() {
        return email;
    }

    public void setStatus(String suspended) {
    }
}

