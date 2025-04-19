package com.example.smartpass;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "passport_app";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(@SuppressLint("RestrictedApi") Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE applicants (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, passport_id TEXT, address TEXT, status TEXT)";
        db.execSQL(query);

        // Insert sample data
        db.execSQL("INSERT INTO applicants (name, passport_id, address, status) VALUES " +
                "('Ravi Kumar', 'P123456789', 'Mumbai', 'Pending')," +
                "('Anjali Sharma', 'P987654321', 'Delhi', 'Pending')" +
                "('Vikas Gupta', 'P123456798', 'Pune', 'Pending')" +
                "('Rohini Rathod', P978645312, 'Mumbai', 'Pending')" +
                "('Pooja Mehta', 'P741852963', 'Amritsar', 'Pending')" +
                "('Soham Patel', 'P369258147', 'Surat', 'Pending')" +
                "('Priya Chauhan', 'P321654987', 'Jaipur', 'Pending')" +
                "('Aarti Patil', 'P951623874', 'Bengluru', 'Pending')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS applicants");
        onCreate(db);
    }

    public List<ApplicantPolice> getAllApplicants() {
        List<ApplicantPolice> applicants = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM applicants", null);
        if (cursor.moveToFirst()) {
            do {
                applicants.add(new ApplicantPolice (
                        ((Cursor) cursor).getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return applicants;
    }

    public void updateStatus(int id, String newStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", newStatus);
        db.update("applicants", values, "id=?", new String[]{String.valueOf(id)});
    }
}
