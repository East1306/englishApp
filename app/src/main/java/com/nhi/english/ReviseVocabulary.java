
package com.nhi.english;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

class Question{
    int id;
    String content;
    String answerA, answerB, answerC, answerD;
}

public class ReviseVocabulary extends AppCompatActivity {
    TextView question;
    TextView explain;
    RadioButton A, B, C, D;
    ImageButton back, next;
    int pos = 0;
    ArrayList<Question> list = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.vocabulary);

        question = (TextView) findViewById(R.id.question);
        explain = (TextView) findViewById(R.id.explain);
        A = (RadioButton) findViewById(R.id.RdbA);
        B = (RadioButton) findViewById(R.id.RdbB);
        C = (RadioButton) findViewById(R.id.RdbC);
        D = (RadioButton) findViewById(R.id.RdbD);
        back = (ImageButton) findViewById(R.id.ic_back);
        next = (ImageButton) findViewById(R.id.ic_next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ReadData();
        Display();


    }

    void ReadData(){
    }
    void Display(){
    }
}

//package com.nhi.english;
//
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.RadioButton;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//
//class Question{
//    int id;
//    String content;
//    String answerA, answerB, answerC, answerD;
//}
//
//public class ReviseVocabulary extends AppCompatActivity {
//    TextView question;
//    TextView explain;
//    RadioButton A, B, C, D;
//    ImageButton back, next;
//    int pos = 0;
//    ArrayList<Question> list = new ArrayList<>();
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.Vocabulary);
//
//        question = (TextView) findViewById(R.id.question);
//        explain = (TextView) findViewById(R.id.explain);
//        A = (RadioButton) findViewById(R.id.RdbA);
//        B = (RadioButton) findViewById(R.id.RdbB);
//        C = (RadioButton) findViewById(R.id.RdbC);
//        D = (RadioButton) findViewById(R.id.RdbD);
//        back = (ImageButton) findViewById(R.id.ic_back);
//        next = (ImageButton) findViewById(R.id.ic_next);
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        ReadData();
//        Display();
//
//
//    }
//
//    void ReadData(){
//    }
//    void Display(){
//    }
//}
