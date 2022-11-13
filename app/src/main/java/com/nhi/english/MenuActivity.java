package com.nhi.english;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;


public class MenuActivity extends AppCompatActivity {
    CardView Cd1, Cd2, Cd3, Cd4, Cd5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Cd1 = findViewById(R.id.card1);
        Cd2 = findViewById(R.id.card2);
        Cd3 = findViewById(R.id.card3);
        Cd4 = findViewById(R.id.card4);
        Cd5 = findViewById(R.id.card5);

        Cd1.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, Vocabulary.class);
            startActivity(intent);
        });

        Cd2.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, Grammar.class);
            startActivity(intent);
        });

        Cd3.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, Listening.class);
            startActivity(intent);
        });

        Cd5.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, Activity_HomeMenu.class);
            startActivity(intent);
        });
    }
}
