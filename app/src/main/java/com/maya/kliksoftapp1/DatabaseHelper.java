package com.maya.kliksoftapp1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "UserDatabase";

    private static final int DATABASE_VERSION = 3;   // 1 more to update

    // Admin credentials and status
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "123";
    private boolean isAdmin = false;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the users table
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE products (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, price INTEGER)");
        // Insert default users
        insertDefaultUsers(db);
    }
    public int getUserId(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM users WHERE username = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        int userId = -1;
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }
        cursor.close();
        return userId;
    }

    // Fetch username
    public String getUserName(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT username FROM users WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});
        String username = null;
        if (cursor.moveToFirst()) {
            username = cursor.getString(0);
        }
        cursor.close();
        return username;
    }


    // Method to insert base users like 'admin' and 'user'
    private void insertDefaultUsers(SQLiteDatabase db) {
        ContentValues adminValues = new ContentValues();
        adminValues.put("username", ADMIN_USERNAME);
        adminValues.put("password", ADMIN_PASSWORD);
        db.insert("users", null, adminValues);

        ContentValues userValues = new ContentValues();
        userValues.put("username", "s");
        userValues.put("password", "s");
        db.insert("users", null, userValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }

    // Method to add a new user
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert("users", null, values);
        return result != -1;
    }

    // Method to check user credentials and set admin status if applicable
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean exists = cursor.getCount() > 0;

        // Set isAdmin to true if credentials match the hardcoded admin values
        isAdmin = exists && ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);

        cursor.close();
        db.close();
        return exists;
    }

    

   //it worksssssssssss
    public String productName;
    public String productDesc;
    public String productPrice;
    public boolean addProduct(String productName, String productDescription,int productPrice){
        this.productName = productName; //
        this.productDesc = productDescription;
        this.productPrice = String.valueOf(productPrice);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", productName);
        values.put("description", productDescription);
        values.put("price", productPrice);
        long result = db.insert("products", null, values);
        return result != -1;
    }
    public boolean isAdmin() {
        return isAdmin;
    }

}
