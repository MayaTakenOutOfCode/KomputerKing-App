package com.maya.kliksoftapp1;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.graphics.Color;
import java.util.List;
import java.util.ArrayList;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText editTextLogin, editTextPassword;
    private Dialog settingsDialog;
    private Dialog shoppingCartDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);


        // Initialize EditText fields
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Initialize the settings dialog
        settingsDialog = new Dialog(this);
        settingsDialog.setContentView(R.layout.popup_layout);
        settingsDialog.setCancelable(true); // Allows dismissing the dialog when back is pressed
        // Initialize shopping cart dialog
        shoppingCartDialog = new Dialog(this);
        shoppingCartDialog.setContentView(R.layout.shopping_carft_popup);
        shoppingCartDialog.setCancelable(true);
        // Set up the back button within the dialog to dismiss it
        settingsDialog.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsDialog.dismiss(); // Close the dialog
            }
        });
        shoppingCartDialog.findViewById(R.id.button_back_shopping).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCartDialog.dismiss();
            }
        });
    }

    public void onNoAccClick(View view) {
        Intent intent = new Intent(this, ActivityRegister.class);
        startActivity(intent);
        finish();
    }
    public void displayAccountName(View view){
        int userId = 1;
        String username = databaseHelper.getUserName(userId);
    }

    public void onSettingsClick(View view){
        settingsDialog.show(); // Show the settings popup dialog
    }


    public void onShoppingCartClick(View view){
        shoppingCartDialog.show();
        Window popupKoszyk = shoppingCartDialog.getWindow();
        GridLayout popupShoppingCartContainer = popupKoszyk.findViewById(R.id.shopping_cart_items_layout);

        TextView shop = new TextView(this);
        shop.setText("wow");
        shop.setTextColor(Color.BLACK);

        shop.setMaxWidth(550);
        popupShoppingCartContainer.addView(shop);
    }


    public void onProfileClick(View view) {
        setContentView(R.layout.profile_page);

        // Retrieve user ID from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", -1); // Default to -1 if not found

        // Fetch the username from the database using the user ID
        String username = databaseHelper.getUserName(userId);

        TextView userNameTextView = findViewById(R.id.userName);
        if (username != null) {
            userNameTextView.setText(username);
        } else {
            userNameTextView.setText("Unknown User");
        }
    }
    public void onBackClick(View view){
        setContentView(R.layout.content_main);
        GeneratorProduct.CreateProducts();
    }



//GridLayout popupshoppingcartContainer = findViewById(R.id.shopping_cart_items_layout);
// GENERATOR product name w shopp





    public void onLoginClick(View view) {
        String username = editTextLogin.getText().toString();
        String password = editTextPassword.getText().toString();

        if (databaseHelper.checkUser(username, password)) {
            int userId = databaseHelper.getUserId(username);

            // Save user ID in SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("USER_ID", userId);
            editor.apply();

            // Navigate to the main content
            setContentView(R.layout.content_main);
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

            // Create products or other actions
            CreateProducts();
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

}
