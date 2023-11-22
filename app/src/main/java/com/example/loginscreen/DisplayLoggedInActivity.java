package com.example.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayLoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_logged_in);

        // Retrieve the username passed from the login screen
        String username = getIntent().getStringExtra("USERNAME");

        // Display the logged-in message with the username
        TextView loggedInMessage = findViewById(R.id.loggedInMessage);
        loggedInMessage.setText("You have successfully logged in as " + username);

        // Set up the logout button
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the login screen
                Intent intent = new Intent(DisplayLoggedInActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity
            }
        });
    }
}
