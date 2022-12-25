//package com.nhi.english.Nhi;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ImageButton;
//import android.view.View;
//import android.widget.RelativeLayout;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//
//import com.nhi.english.R;
//
//public class Grammar extends AppCompatActivity {
//    RelativeLayout BT;
//    CardView B1, B2, B3, B4, B5, B6, B7, B8, B9, B10;
//    ImageButton back;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.grammar);
//        B1 = findViewById(R.id.cardV1);
//        B2 = findViewById(R.id.cardV2);
//        B3 = findViewById(R.id.cardV3);
//        B4 = findViewById(R.id.cardV4);
//        B5 = findViewById(R.id.cardV5);
//        B6 = findViewById(R.id.cardV6);
//        B7 = findViewById(R.id.cardV7);
//        B8 = findViewById(R.id.cardV8);
//        B9 = findViewById(R.id.cardV9);
//        B10 = findViewById(R.id.cardV10);
//
//        //BT = (RelativeLayout) findViewById(R.id.BtnBack);
//        //Phần Intent và Bundle đã giải thích code ở bài trước
//        B1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Grammar.this,U1_Grammar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        B2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Grammar.this,U2_Grammar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        B3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Grammar.this,U3_Grammar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        B4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Grammar.this,U4_Grammar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        B5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Grammar.this,U5_Grammar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        B6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Grammar.this,U6_Grammar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        B7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Grammar.this,U7_Grammar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        B8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Grammar.this,U8_Grammar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        B9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Grammar.this,U9_Grammar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        B10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Grammar.this,U10_Grammar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//    }
//
//}
