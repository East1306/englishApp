package com.nhi.english;

import android.app.Service;
import android.content.Intent;
import androidx.annotation.Nullable;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class BroadcastService_Correctword extends Service{

    private String TAG = "BroadcastService";
    public static final String COUNTDOWN_CDT = "com.nhi.english";
    Intent intent = new Intent(COUNTDOWN_CDT);
    BroadcastService_Quiz broadcastServiceQuiz = null;
    SharedPreferences sharedPreferences;

    CountDownTimer countDownTimer = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Starting timer ...");
        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        long millis = sharedPreferences.getLong("time", 30000);
        if(millis / 1000 == 0){
            millis = 30000;
        }

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished);
                intent.putExtra("countdown", millisUntilFinished);
                sendBroadcast(intent);
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        countDownTimer.cancel();
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}