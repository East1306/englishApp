package com.nhi.english.MinhTriet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


class Correct {
    public String St;
    public String ID;
    public String Q;
    public String Q1;
}
public class Activity_correct extends AppCompatActivity {
    ArrayList<ranking> Ranking = new ArrayList<>();
    MediaPlayer player;
    int Diem1, Star1, Time1, Diem2, Star2, Time2, Diem3, Star3, Time3, Diem4, Star4, Time4, Diem5, Star5, Time5,DiemCheck,time=0,StarCheck,TimeCheck;
    SharedPreferences sharePreferences;
    ArrayList<Correct> L1 = new ArrayList();
    ArrayList<Correct> L2 = new ArrayList();
    TextView VN_Question, EN_Question ,Time, Correct_Answer, Star;
    EditText Answer;
    ImageView btnAnswer, btnSkip;
    CountDownTimer countDownTimer;
    boolean easy = true;
    int pos = 0;
    int countdown = 30;
    int kq=0;
    int dlt = 0;
    int slt = 0;
    int ns = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_correctword);
        sharePreferences = getSharedPreferences("HighScore", MODE_PRIVATE);

        Diem1 = sharePreferences.getInt("Diem1", 0);
        Log.e("check1",""+Diem1);
        Diem2 = sharePreferences.getInt("Diem2", 0);
        Log.e("check2",""+Diem2);
        Diem3 = sharePreferences.getInt("Diem3", 0);
        Log.e("check3",""+Diem3);
        Diem4 = sharePreferences.getInt("Diem4", 0);
        Log.e("check4",""+Diem4);
        Diem5 = sharePreferences.getInt("Diem5", 0);
        Log.e("check5",""+Diem5);

        AnhXa();

        ReadData();
        Collections.shuffle(L1);
        Collections.shuffle(L2);
        StartCountDown();
        Display(pos);

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer_sound();
                if(L1.get(pos).Q.equalsIgnoreCase(Answer.getText().toString()) || L2.get(pos).Q.equalsIgnoreCase(Answer.getText().toString())){
                    Toast.makeText(Activity_correct.this, "You are correct <3",Toast.LENGTH_SHORT).show();
                    easy = false;
                    kq++;
                    dlt++;
                    slt = 0;
                    if(dlt == 3){
                        ns ++;
                        dlt = 0;
                    }
                    NextPage();
                }
                else{
                    Toast.makeText(Activity_correct.this, "You are Wrong !!",Toast.LENGTH_SHORT).show();
                    dlt = 0;
                    slt++;
                    if(slt == 3){
                        ns --;
                        slt = 0;
                    }
                    if(ns < 0){
                        Intent intent = new Intent(Activity_correct.this, activity_correct_play_again.class);
                        startActivity(intent);
                        NextPage();
                    }
                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlt = 0;
                slt++;
                easy = true;
                if(slt == 3){
                    ns --;
                    slt = 0;
                }
                if(ns < 0){
                    Intent intent = new Intent(Activity_correct.this, activity_correct_play_again.class);
                    startActivity(intent);
                }
                NextPage();
            }
        });
    }

    public void Answer_sound(){
        player = MediaPlayer.create(this, R.raw.answer_sound);
        player.start();
    }

    void AnhXa(){
        VN_Question =(TextView) findViewById(R.id.txtVN_Question);
        EN_Question =(TextView) findViewById(R.id.txtEN_Question);
        Answer = (EditText) findViewById(R.id.InputAnswer);
        btnAnswer =(ImageView) findViewById(R.id.btnAnswer);
        btnSkip =(ImageView) findViewById(R.id.btnSkip);
        Time = (TextView) findViewById(R.id.txttime_correctword);
        Correct_Answer = (TextView) findViewById(R.id.txt_correct_answer);
        Star = (TextView) findViewById(R.id.txtstar);
    }

    private String mixWords(String word){

        List<String> words = Arrays.asList(word.split(""));
        Collections.shuffle(words);
        String mixed = "";
        for(String i : words){
            mixed = mixed + " " + i;
        }
        return mixed;
    }

    void StartCountDown()
    {
        countDownTimer = new CountDownTimer(31000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("2","2");
                int count=0;
                count++;
                time+=count;
                if(countdown>9)
                {
                    Time.setTextColor(getResources().getColor(R.color.blue));
                    Time.setText("00:"+String.valueOf(countdown));
                }
                else
                {
                    Time.setTextColor(getResources().getColor(R.color.red));
                    Time.setText("00:0"+String.valueOf(countdown));
                }
                countdown--;
            }
            @Override
            public void onFinish() {
                countdown = 30;
                countDownTimer.cancel();
                StartCountDown();
                pos++;
                easy = true;
                if (pos >= 5) {
                    Load(kq,ns,time);
                    Intent intent = new Intent(Activity_correct.this, activity_ranking.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ",kq);
                    bundle.putInt("Socau",pos);
                    intent.putExtra("MyPackage",bundle);
                    startActivity(intent);
                }
                else {
                    dlt = 0;
                    slt++;
                    if(slt == 3){
                        ns --;
                        slt = 0;
                    }
                    if(ns < 0){
                        Intent intent = new Intent(Activity_correct.this, activity_correct_play_again.class);
                        startActivity(intent);
                    }
                    NextPage();
                }
            }
        }.start();
    }
    void NextPage()
    {
        countdown=30;
        pos++;
        if (pos >= 5) {
            countDownTimer.cancel();
            Load(kq,ns,time);
            Intent intent = new Intent(Activity_correct.this,activity_ranking.class);
            Bundle bundle = new Bundle();
            bundle.putInt("KQ",kq);
            bundle.putInt("Socau",pos);
            intent.putExtra("MyPackage",bundle);
            startActivity(intent);
        }
        else if (ns < 0){
            countDownTimer.cancel();
            Display(pos);
        }
        else {
            countDownTimer.cancel();
            StartCountDown();
            Display(pos); //Hiển thị câu hỏi kế tiếp
        }
    }
    void Display(int i){

        if(easy == true){
            Log.e("1", "Display: Easy_word");
            Answer.setText("");
            VN_Question.setText(L1.get(pos).Q1);
            EN_Question.setText(mixWords(L1.get(i).Q.toUpperCase(Locale.ROOT)));
            Correct_Answer.setText("Correct answer: "+ kq);
            Star.setText(""+ns);
        }
        else {
            Log.e("2", "Display: Hard_word");
            Answer.setText("");
            VN_Question.setText(L2.get(pos).Q1);
            EN_Question.setText(mixWords(L2.get(i).Q.toUpperCase(Locale.ROOT)));
            Correct_Answer.setText("Correct answer: "+ kq);
            Star.setText(""+ns);
        }
    }

    void ReadData() {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("correct_words_data.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node instanceof Element) {

                    Element Item = (Element) node;
                    NodeList listChild;

                    listChild = Item.getElementsByTagName("Style");
                    String Style = listChild.item(0).getTextContent();

                    listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();

                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();

                    listChild = Item.getElementsByTagName("Question1");
                    String Question1 = listChild.item(0).getTextContent();

                    Correct Q1 = new Correct();
                    Correct Q2 = new Correct();
                    if(Style.equalsIgnoreCase("1")){
                        Q1.St = Style;
                        Q1.ID = ID;
                        Q1.Q = Question;
                        Q1.Q1 = Question1;
                        L1.add(Q1);
                    }
                    else{
                        Q2.St = Style;
                        Q2.ID = ID;
                        Q2.Q = Question;
                        Q2.Q1 = Question1;
                        L2.add(Q2);
                    }
                };
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void Load(int kq,int ngoisao,int time) {
        SharedPreferences.Editor editor;
        editor = sharePreferences.edit();

        if (Diem1 == 0) {
            Log.e("1","!");
            editor.putInt("Diem1", kq);
            editor.putInt("Star1", ngoisao);
            editor.putInt("Time1", time);
            Log.e("Diem1",""+kq);
            Log.e("Star1",""+ngoisao);
            Log.e("Time1",""+time);
        } else if (Diem2 == 0) {
            Log.e("2","!");
            editor.putInt("Diem2", kq);
            editor.putInt("Star2", ngoisao);
            editor.putInt("Time2", time);
            Log.e("Diem2",""+kq);
            Log.e("Star2",""+ngoisao);
            Log.e("Time2",""+time);
        } else if (Diem3 == 0) {
            Log.e("3","!");
            editor.putInt("Diem3", kq);
            editor.putInt("Star3", ngoisao);
            editor.putInt("Time3", time);
            Log.e("Diem3",""+kq);
            Log.e("Star3",""+ngoisao);
            Log.e("Time3",""+time);
        } else if (Diem4 == 0) {
            Log.e("4","!");
            editor.putInt("Diem4", kq);
            editor.putInt("Star4", ngoisao);
            editor.putInt("Time4", time);
            Log.e("Diem4",""+kq);
            Log.e("Star4",""+ngoisao);
            Log.e("Time4",""+time);
        } else if (Diem5 == 0) {
            Log.e("5","!");
            editor.putInt("Diem5", kq);
            editor.putInt("Star5", ngoisao);
            editor.putInt("Time5", time);
            Log.e("Diem5",""+kq);
            Log.e("Star5",""+ngoisao);
            Log.e("Time5",""+time);
        }else {
            Log.e("6","!");
            editor.putInt("DiemCheck", kq);
            editor.putInt("StarCheck", ngoisao);
            editor.putInt("TimeCheck", time);
            Log.e("DiemCheck",""+kq);
            Log.e("StarCheck",""+ngoisao);
            Log.e("TimeCheck",""+time);
            DiemCheck = sharePreferences.getInt("DiemCheck", 0);
            StarCheck = sharePreferences.getInt("StarCheck", 0);
            TimeCheck = sharePreferences.getInt("TimeCheck", 0);
            Star1 = sharePreferences.getInt("Star1", 0);
            Time1 = sharePreferences.getInt("Time1", 0);
            Star2 = sharePreferences.getInt("Star2", 0);
            Time2 = sharePreferences.getInt("Time2", 0);
            Star3 = sharePreferences.getInt("Star3", 0);
            Time3 = sharePreferences.getInt("Time3", 0);
            Star4 = sharePreferences.getInt("Star4", 0);
            Time4 = sharePreferences.getInt("Time4", 0);
            Star5 = sharePreferences.getInt("Star5", 0);
            Time5 = sharePreferences.getInt("Time5", 0);
            Ranking.add(new ranking(Diem1, Star1, Time1));//0
            Ranking.add(new ranking(Diem2, Star2, Time2));//1
            Ranking.add(new ranking(Diem3, Star3, Time3));//2
            Ranking.add(new ranking(Diem4, Star4, Time4));//3
            Ranking.add(new ranking(Diem5, Star5, Time5));//4
            Ranking.add(new ranking(DiemCheck, StarCheck, TimeCheck));//5
            Collections.sort(Ranking);
            for (int i = Ranking.size() - 1; i > 5; i--) {
                Ranking.remove(i);
            }

            editor.putInt("Diem1", Ranking.get(0).getSoCau());
            editor.putInt("Star1", Ranking.get(0).getCorrect_Star());
            editor.putInt("Time1", Ranking.get(0).getEar_Finish_Time());
            editor.putInt("Diem2", Ranking.get(1).getSoCau());
            editor.putInt("Star2", Ranking.get(1).getCorrect_Star());
            editor.putInt("Time2", Ranking.get(1).getEar_Finish_Time());
            editor.putInt("Diem3", Ranking.get(2).getSoCau());
            editor.putInt("Star3", Ranking.get(2).getCorrect_Star());
            editor.putInt("Time3", Ranking.get(2).getEar_Finish_Time());
            editor.putInt("Diem4", Ranking.get(3).getSoCau());
            editor.putInt("Star4", Ranking.get(3).getCorrect_Star());
            editor.putInt("Time4", Ranking.get(3).getEar_Finish_Time());
            editor.putInt("Diem5", Ranking.get(4).getSoCau());
            editor.putInt("Star5", Ranking.get(4).getCorrect_Star());
            editor.putInt("Time5", Ranking.get(4).getEar_Finish_Time());

        }
        editor.commit();
    }
}
