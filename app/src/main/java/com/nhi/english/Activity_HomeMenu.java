package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nhi.english.Revise_PhuongDong.ReviseVocabulary;

public class Activity_HomeMenu extends AppCompatActivity {
    private ImageView imgbtnQuiz;
    private ImageView imgbtnCW;
    private ImageView img_revise;
    private ImageView img_listen;
    private Button btn_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_menu);
        imgbtnQuiz = (ImageView) findViewById(R.id.btn_quiz);
        imgbtnCW = (ImageView) findViewById(R.id.btn_correctword);
        btn_back = (Button) findViewById(R.id.btn_goback_practice);
        img_revise = (ImageView) findViewById(R.id.btn_revision);
        img_listen = (ImageView) findViewById(R.id.btn_listen);

        imgbtnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_HomeMenu.this, Numofquestion.class);
                startActivity(intent);
            }
        });

        imgbtnCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_HomeMenu.this, Activity_correctword.class);
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_HomeMenu.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        img_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_HomeMenu.this, Listening.class);
                startActivity(intent);
            }
        });

        img_revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_HomeMenu.this,
                                            ReviseVocabulary.class);
                intent.putExtra("key", 1306);
                startActivity(intent);
            }
        });


    }
}