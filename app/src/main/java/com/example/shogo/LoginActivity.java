package com.example.shogo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_button);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        loginButton.setOnClickListener(view -> {
            if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!username.getText().toString().equals("gab")){
                Toast.makeText(this, "User not found.", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!password.getText().toString().equals("gab")){
                Toast.makeText(this, "Incorrect password.", Toast.LENGTH_SHORT).show();
                return;
            }

            startActivity(new Intent(this, MainActivity.class));
        });
    }
}