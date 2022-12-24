package com.nhi.english.MinhTriet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.PhatLe.activity_ketqua;
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

    ArrayList<Correct> L = new ArrayList();
    TextView VN_Question, EN_Question ,Time, Correct_Answer, Star;
    EditText Answer;
    Button btnAnswer,btnSkip;
    CountDownTimer countDownTimer;
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
        VN_Question =(TextView) findViewById(R.id.txtVN_Question);
        EN_Question =(TextView) findViewById(R.id.txtEN_Question);
        Answer = (EditText) findViewById(R.id.InputAnswer);
        btnAnswer =(Button) findViewById(R.id.btnAnswer);
        btnSkip =(Button) findViewById(R.id.btnSkip);
        Time = (TextView) findViewById(R.id.txttime_correctword);
        Correct_Answer = (TextView) findViewById(R.id.txt_correct_answer);
        Star = (TextView) findViewById(R.id.txtstar);
        ReadData();
        Collections.shuffle(L);
        StartCountDown();
        Display(pos);

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(L.get(pos).Q.equalsIgnoreCase(Answer.getText().toString())){
                    Toast.makeText(Activity_correct.this, "You are correct <3",Toast.LENGTH_SHORT).show();
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
                        Intent intent = new Intent(Activity_correct.this,activity_corect_false.class);
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
                if(slt == 3){
                    ns --;
                    slt = 0;
                }
                if(ns < 0){
                    Intent intent = new Intent(Activity_correct.this,activity_corect_false.class);
                    startActivity(intent);
                }
                NextPage();
            }
        });
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
                Log.e("1","1");
                if(countdown>9)
                {
                    Time.setText("00:"+String.valueOf(countdown));
                }
                else
                {
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
                if (pos >= L.size()) {
                    Intent intent = new Intent(Activity_correct.this, activity_ketqua.class);
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
                        Intent intent = new Intent(Activity_correct.this,activity_corect_false.class);
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
        if (pos >= L.size()) {
            countDownTimer.cancel();;
            Intent intent = new Intent(Activity_correct.this,activity_ketqua.class);
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
        Answer.setText("");
        VN_Question.setText(L.get(pos).Q1);
        EN_Question.setText(mixWords(L.get(i).Q.toUpperCase(Locale.ROOT)));
        Correct_Answer.setText("Correct answer: "+ kq);
        Star.setText(""+ns);
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
//                    if(Style.equalsIgnoreCase("1")){
                        Q1.St = Style;
                        Q1.ID = ID;
                        Q1.Q = Question;
                        Q1.Q1 = Question1;
                        L.add(Q1);
//                    }
//                    else{
//                        Q2.St = Style;
//                        Q2.ID = ID;
//                        Q2.Q = Question;
//                        Q2.Q1 = Question1;
//                        L.add(Q2);
//                    }
                };
//                if(L.size() >= 10){
//                    break;
//                }
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
}
