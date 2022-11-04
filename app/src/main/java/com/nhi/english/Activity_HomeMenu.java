package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Activity_HomeMenu extends AppCompatActivity {
    private ImageButton imgbtnQuiz;
    private ImageButton imgbtnCW;

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
    }

    public void openNumofquestion() {
        Intent intent = new Intent(this, Numofquestion.class);
        startActivity(intent);
    }

    public void openCorrectword() {
        Intent intent = new Intent(this, Activity_correctword.class);
        startActivity(intent);
    }

}