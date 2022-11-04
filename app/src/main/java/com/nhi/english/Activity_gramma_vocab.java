package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_gramma_vocab extends AppCompatActivity {
    private Button btnGobackHomeMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gramma_vocab);

        btnGobackHomeMenu = (Button) findViewById(R.id.btn_goback_Quiz);
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