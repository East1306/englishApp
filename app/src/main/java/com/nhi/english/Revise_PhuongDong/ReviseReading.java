package com.nhi.english.Revise_PhuongDong;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.R;

public class ReviseReading extends AppCompatActivity {
    TextView question, explain;
    RadioButton A, B, C, D;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_reading);

        question = (TextView) findViewById(R.id.questionRead);
        explain = (TextView) findViewById(R.id.explain);
        A = (RadioButton) findViewById(R.id.RdbA);
        B = (RadioButton) findViewById(R.id.RdbB);
        C = (RadioButton) findViewById(R.id.RdbC);
        D = (RadioButton) findViewById(R.id.RdbD);
    }
}
