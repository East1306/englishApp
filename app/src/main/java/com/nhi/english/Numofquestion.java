package com.nhi.english;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Numofquestion extends AppCompatActivity {
    private Button btnGobackHomeMenu, btnStartQuiz;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.num_of_q);


        btnGobackHomeMenu = (Button) findViewById(R.id.btn_goback_NumOfQues);
        btnGobackHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackHomeMenuActivity();
            }
        });

        btnStartQuiz = (Button) findViewById(R.id.btn_startQuiz);
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_gramma_vocab();
            }
        });


    }

    public void gobackHomeMenuActivity() {
        Intent intent = new Intent(this, Activity_HomeMenu.class);
        startActivity(intent);
    }

    public void openActivity_gramma_vocab() {
        Intent intent = new Intent(this, Activity_gramma_vocab.class);
        startActivity(intent);
    }
}



