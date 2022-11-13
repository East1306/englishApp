package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Listening extends AppCompatActivity {

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listen_audio);

        back = findViewById(R.id.BtnBack);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(Listening.this, MenuActivity.class);
            startActivity(intent);
        });
    }

}