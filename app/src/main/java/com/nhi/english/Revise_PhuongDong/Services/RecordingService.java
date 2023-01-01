package com.nhi.english.Revise_PhuongDong.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import com.nhi.english.Revise_PhuongDong.Database.DBHelper;
import com.nhi.english.Revise_PhuongDong.Models.RecordingItem;

import java.io.File;
import java.io.IOException;

public class RecordingService extends Service {

    MediaRecorder mediaRecorder;
    long mStartingTimeMillis = 0;
    long mElapasedMillis = 0;

    File file;

    String fileName;

    DBHelper dbHelper;

    public void OnCreate(){
        super.onCreate();
        dbHelper = new DBHelper(getApplicationContext());
    }

    @Override
    public IBinder onBind (Intent intent){
        return  null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        return START_STICKY;

    }

    private void startRecording() {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        fileName = "audio_" + ts;

        file = new File(Environment.getExternalStorageDirectory()
                          + "/MySoundRecord" + fileName + ".mp3");
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setAudioChannels(1);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mStartingTimeMillis = System.currentTimeMillis();
    }

    private void stopRecording(){
        mediaRecorder.stop();
        mElapasedMillis = System.currentTimeMillis() - mStartingTimeMillis;
        mediaRecorder.release();
        Toast.makeText(getApplicationContext(), "Recording saved "
                                                + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();

        //add to database

        RecordingItem recordingItem = new RecordingItem(fileName, file.getAbsolutePath()
                                                    , mElapasedMillis, System.currentTimeMillis());
        dbHelper.addRecording(recordingItem);
    }

    @Override
    public void onDestroy() {
        if(mediaRecorder != null){
            stopRecording();
        }
        super.onDestroy();
    }
}
