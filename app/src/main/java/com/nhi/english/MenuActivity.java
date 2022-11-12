package com.nhi.english;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


public class MenuActivity extends AppCompatActivity {
    CardView Cd1, Cd2, Cd3, Cd4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Cd1 = findViewById(R.id.card1);
        Cd2 = findViewById(R.id.card2);
        Cd3 = findViewById(R.id.card3);
        Cd4 = findViewById(R.id.card4);

        Cd1.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, Vocabulary.class);
            startActivity(intent);
        });

        Cd2.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, Grammar.class);
            startActivity(intent);
        });
    }
}





