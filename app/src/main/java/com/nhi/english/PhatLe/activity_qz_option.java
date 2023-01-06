package com.nhi.english.PhatLe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nhi.english.R;

public class activity_qz_option extends AppCompatActivity {

    Button btnPlay,btnRanking,btnInstruction,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qz_option);
        btnPlay = (Button) findViewById(R.id.txt_playQZ);
        btnInstruction = (Button) findViewById(R.id.txt_instructionQZ);
        btnBack = (Button) findViewById(R.id.txt_backQZ);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_quiz_play();
            }
        });

        btnInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_quiz_instruciton();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity_home_menu();

            }
        });

    }

    public void openActivity_quiz_play() {
        Intent intent = new Intent(activity_qz_option.this, Numofquestion.class);
        startActivity(intent);
    }

    public void openActivity_quiz_instruciton(){
        Intent intent = new Intent(activity_qz_option.this, Activity_QZ_instruction.class);
        startActivity(intent);}

    public void backActivity_home_menu() {
        finish();
    }
}