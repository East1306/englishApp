package com.nhi.english;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by BillGates on 05/05/2017.
 */
public class Activity_highscores extends Activity {
    TextView Txt1;
    int HighScore;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        Txt1 = (TextView)findViewById(R.id.TxtRanking);
        LoadHighScore();
        Txt1.setText(""+ HighScore);
    }
    void LoadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if (sharedPreferences !=null){
            HighScore = sharedPreferences.getInt("H",0);
        }
    }
}