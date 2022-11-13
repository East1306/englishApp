package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.nhi.english.Revise_PhuongDong.ReviseVocabulary;

public class Activity_HomeMenu extends AppCompatActivity {
    private ImageButton imgbtnQuiz;
    private ImageButton imgbtnCW;
    private ImageButton img_revise;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_menu);

        imgbtnQuiz = (ImageButton) findViewById(R.id.btn_quiz);
        imgbtnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openNumofquestion();
            }
        });

        imgbtnCW = (ImageButton) findViewById(R.id.btn_correctword);
        imgbtnCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCorrectword();
            }
        });

        btn_back = (Button) findViewById(R.id.btn_goback_practice);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPractice();
            }
        });

        img_revise = (ImageButton) findViewById(R.id.btn_revision);
        img_revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_HomeMenu.this, ReviseVocabulary.class);
                startActivity(intent);
            }
        });
    }

    public void openNumofquestion() {
        Intent intent = new Intent(this, Numofquestion.class);
        startActivity(intent);
    }

    public void openCorrectword() {
        Intent intent = new Intent(this, Activity_correctword.class);
        startActivity(intent);
    }

    public void backPractice() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}