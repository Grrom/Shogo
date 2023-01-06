package com.example.shogo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().setTitle("Confirm reservation");

        Button confirmButton = findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(view -> {
            onBackPressed();
            onBackPressed();
            Toast.makeText(this, "This feature is not available yet.", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}