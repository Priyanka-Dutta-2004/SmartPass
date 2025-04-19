package com.example.smartpass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AdminDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SmartPass.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ADMINS = "admins";

    public AdminDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADMINS_TABLE = "CREATE TABLE " + TABLE_ADMINS + " (" +
                "name TEXT NOT NULL, " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL, " +
                "status TEXT DEFAULT 'Active')";
        db.execSQL(CREATE_ADMINS_TABLE);

        // Insert sample admin data
        db.execSQL("INSERT INTO " + TABLE_ADMINS + " (name,id, email, password, status) VALUES " +
                "('Admin Shiwani','ADM001', 'shiw@admin.com', 'password123', 'Active'), " +
                "('Admin Priyanka','ADM002', 'priy@admin.com', 'password456', 'Active')," +
                "('Admin khushi', 'ADM003','Khushi@admin.com', 'password789', 'Active');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINS);
        onCreate(db);
    }

    // Retrieve all admins
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ADMINS, null);
        if (cursor.moveToFirst()) {
            do {
                Admin admin;
                admin = new Admin(
                        String.valueOf(cursor.getString(0)),  // name
                                       cursor.getInt(1),     // id
                                       cursor.getString(2),   // email
                                       cursor.getString(4)   // status
                );
                admins.add(admin);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return admins;
    }

    // Check admin login
    public boolean checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT password FROM " + TABLE_ADMINS + " WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            String storedPassword = cursor.getString(0);
            cursor.close();
            db.close();
            return storedPassword.equals(password); // Plain text comparison for simplicity
        }
        cursor.close();
        db.close();
        return false;
    }
}