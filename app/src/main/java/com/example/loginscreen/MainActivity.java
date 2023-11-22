package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button clearButton;

    private int attemptsLeft = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        clearButton = findViewById(R.id.clearButton);

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        // Set click listener for clear button
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });
    }

    private void attemptLogin() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Validate credentials
        if (username.length() < 4 || password.length() < 4) {
            Toast.makeText(MainActivity.this, "Username and password must be at least 4 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if credentials are correct
        if (username.equals("admin") && password.equals("admin")) {
            // Successful login
            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

            // Start the DisplayLoggedInActivity and pass the username
            Intent intent = new Intent(MainActivity.this, DisplayLoggedInActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);

            // Finish the MainActivity to prevent going back to it using the back button
            finish();
        } else {
            // Incorrect credentials
            attemptsLeft--;

            if (attemptsLeft == 0) {
                // No more attempts left, close the application
                Toast.makeText(MainActivity.this, "Out of attempts. Closing application.", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity
            } else {
                Toast.makeText(MainActivity.this, "Incorrect credentials. Attempts left: " + attemptsLeft, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void clearFields() {
        usernameEditText.getText().clear();
        passwordEditText.getText().clear();
    }
}
