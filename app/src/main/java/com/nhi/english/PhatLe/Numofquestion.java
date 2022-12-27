package com.nhi.english.PhatLe;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.nhi.english.R;


public class Numofquestion extends AppCompatActivity {
    Button btnGobackHomeMenu, btnStartQuiz;
    Spinner spinner;
    int vitri;
    String Arr[] = {"5", "6", "7", "8", "9", "10"};
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

        spinner = (Spinner) findViewById(R.id.btn_spinner);
        CreateSpinner();

    }

    public void gobackHomeMenuActivity() {
        Intent intent = new Intent(this, Activity_HomeMenu.class);
        startActivity(intent);
    }

    public void openActivity_gramma_vocab() {
        Intent intent = new Intent(this, Activity_Quiz.class);
        intent.putExtra("pos", vitri);
        startActivity(intent);
    }
    public void CreateSpinner(){
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                vitri = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                vitri = -1;
            }
        });
    }

}



