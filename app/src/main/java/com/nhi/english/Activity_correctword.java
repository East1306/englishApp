package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_correctword extends AppCompatActivity {
    private Button btnGobackHomeMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_correctword);

        btnGobackHomeMenu = (Button) findViewById(R.id.btn_goback_CW);
        btnGobackHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackHomeMenuActivity();
            }
        });

    }

    public void gobackHomeMenuActivity() {
        Intent intent = new Intent(this, Activity_HomeMenu.class);
        startActivity(intent);
    }

}