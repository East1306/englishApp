package com.nhi.english.PhatLe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.nhi.english.MainActivity;
import com.nhi.english.MinhTriet.Listening;
import com.nhi.english.R;
import com.nhi.english.Revise_PhuongDong.Revise;
import com.nhi.english.MinhTriet.activity_correct_option;

public class Activity_HomeMenu extends AppCompatActivity {
    TextView user;
    ImageView imgbtnQuiz;
    ImageView imgbtnCW;
    ImageView img_revise;
    ImageView img_listen;
    ImageView logout;
    MediaPlayer player;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_menu);
        imgbtnQuiz = (ImageView) findViewById(R.id.btn_Quiz);
        imgbtnCW = (ImageView) findViewById(R.id.btn_correctword);
        img_revise = (ImageView) findViewById(R.id.btn_revision);
        img_listen = (ImageView) findViewById(R.id.btn_listen);
        logout = findViewById(R.id.logout);
        user = findViewById(R.id.user);

        Intent intent = getIntent();
        user.setText(intent.getStringExtra("User"));

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent intent_ = new Intent(Activity_HomeMenu.this
                                                            , MainActivity.class);
                startActivity(intent_);
            }
        });
    }

    public void signOut() {
        gsc.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
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

