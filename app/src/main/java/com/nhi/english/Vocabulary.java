package com.nhi.english;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Vocabulary extends AppCompatActivity {
    RelativeLayout BT;
    CardView B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15;
    ImageButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voca);
        back = findViewById(R.id.BtnBack);
        B1 = findViewById(R.id.cardV1);
        B2 = findViewById(R.id.cardV2);
        B3 = findViewById(R.id.cardV3);
        B4 = findViewById(R.id.cardV4);
        B5 = findViewById(R.id.cardV5);
        B6 = findViewById(R.id.cardV6);
        B7 = findViewById(R.id.cardV7);
        B8 = findViewById(R.id.cardV8);
        B9 = findViewById(R.id.cardV9);
        B10 = findViewById(R.id.cardV10);
        B11 = findViewById(R.id.cardV11);
        B12 = findViewById(R.id.cardV12);
        B13 = findViewById(R.id.cardV13);
        B14 = findViewById(R.id.cardV14);
        B15 = findViewById(R.id.cardV15);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(Vocabulary.this,MenuActivity.class);
            startActivity(intent);
            finish();
        });
        //BT = (RelativeLayout) findViewById(R.id.BtnBack);
        //Phần Intent và Bundle đã giải thích code ở bài trước
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U1_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U2_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U3_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U4_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U5_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U6_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U7_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U8_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U9_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U10_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U11_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U12_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U13_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U14_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
        B15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vocabulary.this,U15_Vocabulary.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
