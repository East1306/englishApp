package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity_correctword extends AppCompatActivity {
    TextView txt;
    String TAG = "Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_correctword);
        txt = findViewById(R.id.txttime_correctword);
        Log.i(TAG, "Started Service");
    }
}