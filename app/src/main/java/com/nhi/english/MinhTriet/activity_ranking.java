package com.nhi.english.MinhTriet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.R;

import java.util.ArrayList;

public class activity_ranking extends AppCompatActivity {
    int Diem1, Star1, Time1, Diem2, Star2, Time2, Diem3, Star3, Time3, Diem4, Star4, Time4, Diem5, Star5, Time5;
    SharedPreferences sharePreferences;
    ArrayList<ranking> Ranking = new ArrayList<>();;
    ranking_adapter Ranking_adapter;
    ListView lv_Ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        AnhXa();
        sharePreferences = getSharedPreferences("HighScore", MODE_PRIVATE);
        Diem1 = sharePreferences.getInt("Diem1", 0);
        Star1 = sharePreferences.getInt("Star1", 0);
        Time1 = sharePreferences.getInt("Time1", 0);
        Diem2 = sharePreferences.getInt("Diem2", 0);
        Star2 = sharePreferences.getInt("Star2", 0);
        Time2 = sharePreferences.getInt("Time2", 0);
        Diem3 = sharePreferences.getInt("Diem3", 0);
        Star3 = sharePreferences.getInt("Star3", 0);
        Time3 = sharePreferences.getInt("Time3", 0);
        Diem4 = sharePreferences.getInt("Diem4", 0);
        Star4 = sharePreferences.getInt("Star4", 0);
        Time4 = sharePreferences.getInt("Time4", 0);
        Diem5 = sharePreferences.getInt("Diem5", 0);
        Star5 = sharePreferences.getInt("Star5", 0);
        Time5 = sharePreferences.getInt("Time5", 0);

        if (Diem1 != 0) {
            Ranking.add(new ranking(Diem1, Star1, Time1));
        }if (Diem2 != 0) {
            Ranking.add(new ranking(Diem2, Star2, Time2));
        }if (Diem3 != 0) {
            Ranking.add(new ranking(Diem3, Star3, Time3));
        }if (Diem4 != 0) {
            Ranking.add(new ranking(Diem4, Star4, Time4));
        }
        if (Diem5 != 0) {
            Ranking.add(new ranking(Diem5, Star5, Time5));
        }
        Log.e("size",""+Ranking.size());
        Log.e("ranking",""+Ranking);
        Ranking_adapter = new ranking_adapter(Ranking, activity_ranking.this, R.layout.only_rank);
        lv_Ranking.setAdapter(Ranking_adapter);
    }

    void AnhXa() {
        lv_Ranking = (ListView) findViewById(R.id.Lv_HighScore);
    }
}
