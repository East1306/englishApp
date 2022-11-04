package com.nhi.english.Revise_PhuongDong;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.R;
import com.nhi.english.Revise_PhuongDong.Question;

import java.util.ArrayList;

public class ReviseListen extends AppCompatActivity {
    Button playSound;
    ImageButton next, back;
    TextView time, question,explain;
    RadioButton A, B, C, D;
    ArrayList<Question> list = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listening);

        playSound = (Button) findViewById(R.id.buttonPlaySound);
        next = (ImageButton) findViewById(R.id.ic_next);
        back = (ImageButton) findViewById(R.id.ic_back);
        time = (TextView) findViewById(R.id.timePlaySound);
        explain = (TextView) findViewById(R.id.explain);
        question = (TextView) findViewById(R.id.contentListen);
        A = (RadioButton) findViewById(R.id.RdbA);
        B = (RadioButton) findViewById(R.id.RdbB);
        C = (RadioButton) findViewById(R.id.RdbC);
        D = (RadioButton) findViewById(R.id.RdbD);

        ReadData();
        DisplayData();

        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    void ReadData(){
    }

    void DisplayData(){
    }

}
