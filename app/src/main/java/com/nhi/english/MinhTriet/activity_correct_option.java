package com.nhi.english.MinhTriet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.nhi.english.PhatLe.Activity_HomeMenu;
import com.nhi.english.R;

public class activity_correct_option extends AppCompatActivity {

    Button btnPlay,btnRanking,btnInstruction,btnBack;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_option);

        btnPlay = (Button) findViewById(R.id.txt_playCW);
        btnRanking = (Button) findViewById(R.id.txt_rankingCW);
        btnInstruction = (Button) findViewById(R.id.txt_instructionCW);
        btnBack = (Button) findViewById(R.id.txt_backCW);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_correct_word_play();
            }
        });

        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_correct_ranking();
            }
        });

        btnInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_correct_instruciton();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity_home_menu();

            }
        });

    }

    public void openActivity_correct_word_play() {
        Intent intent = new Intent(activity_correct_option.this, Activity_correct.class);
        startActivity(intent);
    }


    public void openActivity_correct_ranking() {
        Intent intent = new Intent(activity_correct_option.this, activity_ranking.class);
        startActivity(intent);
    }


    public void openActivity_correct_instruciton(){  Intent intent = new Intent(activity_correct_option.this, Activity_CW_instruction.class);
        startActivity(intent);}


    public void backActivity_home_menu() {
        finish();
    }
}