package com.nhi.english.PhatLe;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nhi.english.MinhTriet.Listening;
import com.nhi.english.R;
import com.nhi.english.Revise_PhuongDong.Revise;
import com.nhi.english.MinhTriet.activity_correct_option;

public class Activity_HomeMenu extends AppCompatActivity {
    ImageView imgbtnQuiz;
    ImageView imgbtnCW;
    ImageView img_revise;
    ImageView img_listen;
    MediaPlayer player;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_menu);
        imgbtnQuiz = (ImageView) findViewById(R.id.btn_Quiz);
        imgbtnCW = (ImageView) findViewById(R.id.btn_correctword);
        img_revise = (ImageView) findViewById(R.id.btn_revision);
        img_listen = (ImageView) findViewById(R.id.btn_listen);

        imgbtnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_HomeMenu.this, activity_qz_option.class);
                startActivity(intent);
            }
        });

        imgbtnCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_HomeMenu.this, activity_correct_option.class);
                startActivity(intent);
            }
        });

        img_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_HomeMenu.this, Listening.class);
                startActivity(intent);
            }
        });

        img_revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_HomeMenu.this, Revise.class);
                startActivity(intent);
            }
        });
    }

}

//        img_highscore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Activity_HomeMenu.this, activity_ranking.class);
//                startActivity(intent);
//            }
//        });

