package com.nhi.english.MinhTriet;

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

public class activity_correct_play_again extends AppCompatActivity {
    Button BT;
    TextView Text;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_again);
        Text = (TextView) findViewById(R.id.Txtfalse);
        BT = (Button)findViewById(R.id.Btn_Home);
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_correct_play_again.this, activity_correct_option.class);
                startActivity(intent);
            }
        });
    }
}
