//package com.nhi.english.Nhi;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.os.Handler;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.SeekBar;
//import android.widget.TextView;
//
//import com.nhi.english.R;
//
//public class U1_Grammar extends AppCompatActivity {
//    ImageButton Btn;
//    TextView playerPosition, playerDuration, Txt0, Txt1, Txt2,Txt3,TxtContent;
//    SeekBar seekBar;
//    ImageView btPlay, btPause;
//    MediaPlayer mediaPlayer;
//    Handler handler = new Handler();
//    Runnable runnable;
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
////        playerPosition = findViewById(R.id.player_position);
////        playerDuration = findViewById(R.id.player_duration);
////        seekBar = findViewById(R.id.seek_bar);
////        btPlay = findViewById(R.id.bt_play);
////        btPause = findViewById(R.id.bt_pause);
//        Txt1.setText(R.string.u1);
//        Txt2.setText(R.string.u2);
//        Txt3.setText(R.string.u2t);
//        Txt0.setText(R.string.u1t);
//        TxtContent.setText(R.string.u1tc);
////        mediaPlayer = MediaPlayer.create(this,R.raw.unit01);
//        Btn.setOnClickListener(v -> {
//            Intent intent = new Intent(U1_Grammar.this,Grammar.class);
//            startActivity(intent);
//            finish();
//        });
////        runnable = new Runnable() {
////            @Override
////            public void run() {
////                seekBar.setProgress(mediaPlayer.getCurrentPosition());
////                handler.postDelayed(this, 500);
////            }
////        };
////        int duration = mediaPlayer.getDuration();
//
////        String sDuration = convertFornat(duration);
////
////        playerDuration.setText(sDuration);
////        btPlay.setOnClickListener(new View.OnClickListener(){
////            @Override
////            public void onClick(View view) {
////                btPlay.setVisibility(View.GONE);
////                btPause.setVisibility(View.VISIBLE);
////                mediaPlayer.start();
////                seekBar.setMax(mediaPlayer.getDuration());
////                handler.postDelayed(runnable, 0);
////            }
////        } );
//
////        btPause.setOnClickListener(new View.OnClickListener() {
////                                       @Override
////                                       public void onClick(View view) {
////                                           btPause.setVisibility(View.GONE);
////
////                                           btPlay.setVisibility(View.VISIBLE);
////
////                                           mediaPlayer.pause();
////
////                                           handler.removeCallbacks(runnable);
////                                       }
////                                   }
////        );
////        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
////            @Override
////            public void onProgressChanged(SeekBar seekBar, int progress, boolean formUser) {
////                if(formUser){
////                    mediaPlayer.seekTo(progress);
////                }
////                playerPosition.setText(convertFornat(mediaPlayer.getCurrentPosition()));
////
////            }
////
////            @Override
////            public void onStartTrackingTouch(SeekBar seekBar) {
////
////            }
////
////            @Override
////            public void onStopTrackingTouch(SeekBar seekBar) {
////
////            }
////        });
////        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
////            @Override
////            public void onCompletion(MediaPlayer mediaPlayer) {
////                btPause.setVisibility(View.GONE);
////
////                btPlay.setVisibility(View.VISIBLE);
////
////                mediaPlayer.seekTo(0);
////            }
////        });
//    }
//
//
//}
