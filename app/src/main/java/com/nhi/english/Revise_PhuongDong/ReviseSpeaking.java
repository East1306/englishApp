package com.nhi.english.Revise_PhuongDong;

import android.Manifest;
import android.content.ContextWrapper;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nhi.english.R;

import java.io.File;
import java.util.ArrayList;


public class ReviseSpeaking extends AppCompatActivity {
    private static int MICROPHONE_PERMISSION_CODE = 200;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    ImageButton back;
    Button record, stop, play, submit;

    //Giữ những câu hỏi _ câu trả lời từ class Revise qua
    ArrayList<Question> listQuestion = new ArrayList<>();
    ArrayList<Answer> listAnswer = new ArrayList<>();
    String sound, result;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaking);

        record = (Button) findViewById(R.id.buttonRecord);
        stop = (Button) findViewById(R.id.buttonStop);
        play = (Button) findViewById(R.id.buttonPlay);
        submit = (Button) findViewById(R.id.buttonSubmit);
        back = (ImageButton) findViewById(R.id.ic_back);

        Intent callerIntent = getIntent();
        //Lấy Bundle dựa vào Revise
        Bundle packageFormCaller= callerIntent.getBundleExtra("Revise");
        listQuestion = packageFormCaller.getParcelableArrayList("Question");
        listAnswer = packageFormCaller.getParcelableArrayList("Answer");
        sound = packageFormCaller.getString("Sound");
        result = packageFormCaller.getString("Result");
        Log.d("ID Session", sound);
        for (Question i: listQuestion){
            Log.d("Question", i.content);
        }

        if(isMicrophonePresent()){
            getMicrophonePermission();
        }

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setOutputFile(getFilePath());
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.prepare();
                    mediaRecorder.start();

                    Toast.makeText(ReviseSpeaking.this, "Recording is started", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.release();
                mediaRecorder = null;

                Toast.makeText(ReviseSpeaking.this, "Recording is stopped", Toast.LENGTH_SHORT).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(getFilePath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    Toast.makeText(ReviseSpeaking.this, "Sarting play", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviseSpeaking.this,
                                                        Revise.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("Question", listQuestion);
                bundle.putParcelableArrayList("Answer", listAnswer);
                bundle.putString("Sound", sound);
                bundle.putString("Result", result);
                intent.putExtra("Speaking", bundle);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviseSpeaking.this, Result.class);
                intent.putExtra("Result", result);
                intent.putExtra("Total sentense", String.valueOf(listQuestion.size()));
                startActivity(intent);
            }
        });
    }

//    public void btnRecord(){
//        try {
//            mediaRecorder = new MediaRecorder();
//            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//            mediaRecorder.setOutputFile(getFilePath());
//            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//            mediaRecorder.prepare();
//            mediaRecorder.start();
//
//            Toast.makeText(this, "Recording is started", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void btnStop(){
//        mediaRecorder.stop();
//        mediaRecorder.release();
//        mediaRecorder = null;
//
//        Toast.makeText(this, "Recording is stopped", Toast.LENGTH_SHORT).show();
//    }
//
//    public void btnPlay(){
//
//        try {
//            mediaPlayer = new MediaPlayer();
//            mediaPlayer.setDataSource(getFilePath());
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//
//            Toast.makeText(this, "Sarting play", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    private boolean isMicrophonePresent (){
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        }else{
            return false;
        }
    }

    private void getMicrophonePermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }

    private String getFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "recordingAudio" + ".mp3");
        return file.getPath();
    }
}

