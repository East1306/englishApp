//package com.nhi.english.Nhi;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import com.nhi.english.R;
//
//public class U2_Grammar extends AppCompatActivity {
//    ImageButton Btn;
//    TextView playerPosition, playerDuration, Txt0, Txt1, Txt2,Txt3,TxtContent;
//    Handler handler = new Handler();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.grammar1);
//        Btn = findViewById(R.id.imageBtn);
//        Txt1 = findViewById(R.id.Txt1);
//        Txt2= findViewById(R.id.Txt2);
//        Txt3= findViewById(R.id.Txt3);
//        Txt0 = findViewById(R.id.Txt0);
//        TxtContent = findViewById(R.id.TxtContent);
//
//        Txt1.setText(R.string.u3);
//        Txt2.setText(R.string.u4);
//        Txt3.setText(R.string.u4t);
//        Txt0.setText(R.string.u3t);
//        TxtContent.setText(R.string.u3tc);
////        mediaPlayer = MediaPlayer.create(this,R.raw.unit01);
//        Btn.setOnClickListener(v -> {
//            Intent intent = new Intent(U2_Grammar.this,Grammar.class);
//            startActivity(intent);
//            finish();
//        });
//
//    }
//
//
//}
