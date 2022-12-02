package com.nhi.english;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;


public class MenuActivity extends AppCompatActivity {
    CardView Cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Cd = findViewById(R.id.card5);
        Cd.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity_HomeMenu.class);
            startActivity(intent);
        });
    }
}
