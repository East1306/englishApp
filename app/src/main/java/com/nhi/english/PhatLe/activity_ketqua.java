package com.nhi.english.PhatLe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nhi.english.R;

public class activity_ketqua extends Activity {
    Button BT;
    TextView KQ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);
        KQ = (TextView) findViewById(R.id.TxtKQ);
        BT = (Button)findViewById(R.id.BtnBack);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("MyPackage");
        KQ.setText(packageFromCaller.getInt("KQ")+"/"+
                packageFromCaller.getInt("Socau"));
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_ketqua.this, Activity_HomeMenu.class);
                startActivity(intent);
            }
        });
    }
}

