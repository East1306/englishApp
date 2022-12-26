package com.nhi.english.MinhTriet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nhi.english.PhatLe.Activity_HomeMenu;
import com.nhi.english.R;

public class Activity_CW_instruction extends AppCompatActivity {

    Button btnBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_correct_word_instruction);

        btnBack = (Button) findViewById(R.id.btn_QuizBack_Instruc);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity_correct_menu();
            }
        });

    }

    public void backActivity_correct_menu() { Intent intent = new Intent(this, Activity_HomeMenu.class);
        startActivity(intent);}
    }