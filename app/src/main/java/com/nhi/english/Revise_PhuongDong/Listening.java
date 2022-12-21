package com.nhi.english.Revise_PhuongDong;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Listening extends AppCompatActivity {
    MediaPlayer sound;
    int countPlaying = 0;
    ArrayList<Integer> tmp = new ArrayList<>();


    public String RandomSession (){
        tmp.add(1);
        tmp.add(2);
        tmp.add(3);
        String id = null;
        for (int i = 0; i < tmp.size(); i++) { //random id bài nghe bất kì
            int index = (int) Math.random() * tmp.size();
            id = tmp.get(index).toString();
            break;
        }
        return id;
    }


    public void PlaySound (Context context, ImageView playSound,
                           SeekBar mSeekBarTime, String idSession){
        creatSound(context, idSession);
        Toast.makeText(context, "You have two listens", Toast.LENGTH_LONG).show();

        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++countPlaying;
                if (countPlaying <= 2 || sound == null) {
                    creatSound(context, idSession);
                }else{
                    Toast.makeText(context, "Listens are over", Toast.LENGTH_LONG).show();
                }
                if(!sound.isPlaying()){
                    sound.start();
                    playSound.setImageResource(R.drawable.ic_baseline_pause_24);
                }else{
                    sound.pause();
                    playSound.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }
            }
        });

        mSeekBarTime.setMax(sound.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mSeekBarTime.setProgress(sound.getCurrentPosition());
            }
        }, 0, 900);
        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playSound.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            }
        });
        mSeekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sound.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    private MediaPlayer creatSound(Context context, String idSession) {
        switch (idSession) {
            case "1":
                sound = MediaPlayer.create(context, R.raw.multiplechoicel_practice1a);
                break;
            case "2":
                sound = MediaPlayer.create(context, R.raw.multiplechoicel_practice2a);
                break;
            case "3":
                sound = MediaPlayer.create(context, R.raw.multiplechoicel_practice3a);
        }
        return sound;
    }

}
