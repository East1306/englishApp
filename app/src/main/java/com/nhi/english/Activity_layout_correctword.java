package com.nhi.english;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Activity_layout_correctword extends AppCompatActivity {

    TextView Question;
    EditText Answer;
    Button btAnswer,btSkip;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_correctword);

        Question = findViewById(R.id.txtQuestion);
        Answer = findViewById(R.id.InputAnswer);
        btAnswer = findViewById(R.id.btnAnswer);
        btSkip = findViewById(R.id.btnSkip);

    }

    private String mixWords(String word){

        List<String> words = Arrays.asList(word.split(: ""));

        Collections.shuffle(words);
        String mixed = "";

        for(String i : words){

            mixed = mixed + i;
        }

        return mixed;
    }
}
