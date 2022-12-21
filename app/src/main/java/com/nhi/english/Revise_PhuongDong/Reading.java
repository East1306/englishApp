package com.nhi.english.Revise_PhuongDong;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.R;

public class Reading extends AppCompatActivity {
    TextView content;
    ImageButton back, next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_reading);

        content = (TextView) findViewById(R.id.reading);
        back = (ImageButton) findViewById(R.id.ic_back);
        next = (ImageButton) findViewById(R.id.ic_next);

        ReadData();
        Display();
    }

    void ReadData(){

    }

    void Display(){

    }
}
