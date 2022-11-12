package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class U4_Grammar extends AppCompatActivity {
    ImageButton Btn;
    TextView Txt0, Txt1, Txt2,Txt3,TxtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grammar1);
        Btn = findViewById(R.id.imageBtn);
        Txt1 = findViewById(R.id.Txt1);
        Txt2= findViewById(R.id.Txt2);
        Txt3= findViewById(R.id.Txt3);
        Txt0 = findViewById(R.id.Txt0);
        TxtContent = findViewById(R.id.TxtContent);

        Txt1.setText(R.string.d3);
        Txt2.setText(R.string.d4);
        Txt3.setText(R.string.d5);
        Txt0.setText(R.string.d2);
        TxtContent.setText(R.string.d1);
//        mediaPlayer = MediaPlayer.create(this,R.raw.unit01);
        Btn.setOnClickListener(v -> {
            Intent intent = new Intent(U4_Grammar.this,Grammar.class);
            startActivity(intent);
            finish();
        });

    }
}
