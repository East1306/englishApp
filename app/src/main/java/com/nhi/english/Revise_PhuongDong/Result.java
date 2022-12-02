package com.nhi.english.Revise_PhuongDong;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.R;


public class Result extends AppCompatActivity {
    TextView result;
    Button finish;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.result_dong);

        result = (TextView) findViewById(R.id.getPoint);
        finish = (Button) findViewById(R.id.finish);


    }
}
