package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nhi.english.Revise_PhuongDong.ReviseVocabulary;

public class Activity_HomeMenu extends AppCompatActivity {
    private ImageView imgbtnQuiz;
    private ImageView imgbtnCW;
    private ImageView img_revise;
    private ImageView img_listen;
    private ImageView img_highscore;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_menu);
        imgbtnQuiz = (ImageView) findViewById(R.id.btn_quiz);
        imgbtnCW = (ImageView) findViewById(R.id.btn_correctword);
        img_revise = (ImageView) findViewById(R.id.btn_revision);
        img_listen = (ImageView) findViewById(R.id.btn_listen);
        img_highscore = (ImageView) findViewById(R.id.btn_highscore);

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
                Intent intent = new Intent(Activity_HomeMenu.this, Activity_layout_correct.class);
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

        img_highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_HomeMenu.this, Activity_highscores.class);
                startActivity(intent);
            }
        });
    }
}