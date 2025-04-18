package com.example.smartpass;

// User class to hold user data
public class User {
    public String name;
    public String phone;
    public int age;
    public String email;
    public String password;

    // Default constructor (required for Firebase)
    public User() {
    }

    // Parameterized constructor
    public User(String name, String phone, int age, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.email = email;
        this.password = password;
    }
}
