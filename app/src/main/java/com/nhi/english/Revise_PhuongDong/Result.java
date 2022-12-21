package com.nhi.english.Revise_PhuongDong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.PhatLe.Activity_HomeMenu;
import com.nhi.english.R;


public class Result extends AppCompatActivity {
    TextView result;
    Button finish;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_dong);

        result = (TextView) findViewById(R.id.getPoint);
        finish = (Button) findViewById(R.id.finish);

        Intent intent = getIntent();
        result.setText("Correct: " +
                        intent.getStringExtra("Result") + "/" +
                        intent.getStringExtra("Total sentense"));
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result.this, Activity_HomeMenu.class);
                startActivity(intent);
            }
        });
    }
}
