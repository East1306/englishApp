package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_gramma_vocab extends AppCompatActivity {
    private Button btnGobackHomeMenu;
    TextView txt;
    String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gramma_vocab);

        txt = findViewById(R.id.txttime);
        Intent intent = new Intent(this, BroadcastService.class);
        startService(intent);
        Log.i(TAG, "Started Service");

        btnGobackHomeMenu = (Button) findViewById(R.id.btn_goback_Quiz);
        btnGobackHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackHomeMenuActivity();
            }
        });
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(BroadcastService.COUNTDOWN_CDT));
        Log.i(TAG, "Registered broadcast receiver");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
        Log.i(TAG, "Unregistered broadcast receiver");
    }

    @Override
    protected void onStop() {
        try {
            unregisterReceiver(broadcastReceiver);
        }catch (Exception e){

        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, BroadcastService.class));
        Log.i(TAG, "Stopped service");
        super.onDestroy();
    }

    private void updateGUI(Intent intent){
        if (intent.getExtras() != null){
            long millisUntilFinished = intent.getLongExtra("countdown", 30000);
            Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished/1000);

            txt.setText(Long.toString(millisUntilFinished / 1000));
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
            sharedPreferences.edit().putLong("time", millisUntilFinished).apply();
        }
    }

    public void gobackHomeMenuActivity() {
        Intent intent = new Intent(this, Activity_HomeMenu.class);
        startActivity(intent);
    }



}