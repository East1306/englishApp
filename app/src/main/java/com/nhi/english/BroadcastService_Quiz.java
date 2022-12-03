package com.nhi.english;

import android.app.Service;
import android.content.Intent;
import androidx.annotation.Nullable;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

public class BroadcastService_Quiz extends Service{

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
       long millis = sharedPreferences.getLong("time", 20000);
       if(millis / 1000 == 0){
           millis = 20000;
       }

       countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished);

                intent.putExtra("countdown", millisUntilFinished);
                sendBroadcast(intent);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: Timer is finished");
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
