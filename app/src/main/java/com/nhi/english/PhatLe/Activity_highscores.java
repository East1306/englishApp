package com.nhi.english.PhatLe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import androidx.annotation.Nullable;

import com.nhi.english.R;

public class Activity_highscores extends Activity {
    TextView Txt1;
    Button btn;
    int HighScore;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        Txt1 = (TextView)findViewById(R.id.txtKQhighscore);
        btn = (Button)findViewById(R.id.Btnbackhighscore);
        LoadHighScore();
        Txt1.setText(""+ HighScore);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_highscores.this, Activity_HomeMenu.class);
                startActivity(intent);
            }
        });

    }


    void LoadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if (sharedPreferences != null){
            HighScore = sharedPreferences.getInt("H",0);
        }
    }
}