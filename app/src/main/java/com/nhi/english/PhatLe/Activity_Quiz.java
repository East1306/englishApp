package com.nhi.english.PhatLe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class QuestionNare {
    public String ID,title,text;
    public String Q;
    public String AnswerA, AnswerB, AnswerC, AnswerD, Answer;
}
public class Activity_Quiz extends AppCompatActivity {
    String TAG = "Main";
    TextView Cauhoi,Ketqua,txt;
    RadioGroup RG;
    Button BT;
    RadioButton A,B,C,D;
    CountDownTimer countDownTimer;
    int pos=0;//vị trí câu hỏi trong danh sách
    int soCau;
    int kq=0; //lưu số câu trả lời đúng
    int HighScore = 0;
    ArrayList <QuestionNare> L ; //chứa câu hỏi
    int countdown = 20;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gramma_vocab);
        L = new ArrayList<>();
        txt = findViewById(R.id.txttime);
        Cauhoi = (TextView) findViewById(R.id.TxtCauhoi);
        Ketqua = (TextView)findViewById(R.id.TxtKetqua);
        RG = (RadioGroup)findViewById(R.id.RadioGroupTraloi);
        BT = (Button) findViewById(R.id.BtnTraloi);
        A = (RadioButton)findViewById(R.id.RdbA);
        B = (RadioButton)findViewById(R.id.RdbB);
        C = (RadioButton)findViewById(R.id.RdbC);
        D = (RadioButton)findViewById(R.id.RdbD);

        Intent intent_ = getIntent();
        soCau = intent_.getExtras().getInt("pos");
        ReadData();
        Collections.shuffle(L);
        StartCountDown();
        Display(pos);

        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idCheck = RG.getCheckedRadioButtonId();
                switch (idCheck) {
                    case R.id.RdbA:
                        if (L.get(pos).Answer.compareTo("A")==0)
                        {
                            kq = kq+1;
                        }
                        NextPage();
                        break;
                    case R.id.RdbB:
                        if (L.get(pos).Answer.compareTo("B")==0)
                        {
                            kq = kq+1;
                        }
                        NextPage();
                        break;
                    case R.id.RdbC:
                        if (L.get(pos).Answer.compareTo("C")==0)
                        {
                            kq = kq+1;
                        }
                        NextPage();
                        break;
                    case R.id.RdbD:
                        if (L.get(pos).Answer.compareTo("D")==0)
                        {
                            kq = kq+1;
                        }
                        NextPage();
                        break;
                }
            }
        });
    }

    void Display(int i){
        Log.i(TAG, "Started Service");
        Cauhoi.setText(L.get(i).Q);
        A.setText(L.get(i).AnswerA);
        B.setText(L.get(i).AnswerB);
        C.setText(L.get(i).AnswerC);
        D.setText(L.get(i).AnswerD);
        Ketqua.setText("Điểm : "+String.valueOf(kq));
        RG.clearCheck();
    }
    void ReadData() {
        try {
            Log.e("","size");
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("quiz_data.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node instanceof Element) {
                    Element Item = (Element) node;

                    NodeList listChild;
                    listChild = Item.getElementsByTagName("ID");

                    String ID = listChild.item(0).getTextContent();

                    listChild = Item.getElementsByTagName("Question");

                    String Question = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerA");
                    String AnswerA = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerB");
                    String AnswerB = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerC");
                    String AnswerC = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerD");
                    String AnswerD = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Answer");
                    String Answer = listChild.item(0).getTextContent();

                    QuestionNare Q1 = new QuestionNare();
                    Q1.ID = ID;
                    Q1.Q = Question;
                    Q1.AnswerA = AnswerA;
                    Q1.AnswerB = AnswerB;
                    Q1.AnswerC = AnswerC;
                    Q1.AnswerD = AnswerD;
                    Q1.Answer = Answer;
                    L.add(Q1);
                    if(L.size() == Chon(soCau)){
                        break;
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


    void LoadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",
                Context.MODE_PRIVATE);
        if (sharedPreferences !=null){
            HighScore = sharedPreferences.getInt("H",0);
        }
    }
    void SaveHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("H",HighScore);
        editor.apply();
    }


    int Chon(int vitri)
    {
        int Z = 0;
        switch (vitri)
        {
            case 0:
                Z = 5;
                break;
            case 1:
                Z = 6;
                break;
            case 2:
                Z = 7;
                break;
            case 3:
                Z = 8;
                break;
            case 4:
                Z = 9;
            case 5:
                Z = 10;
        }
        return Z;
    }

    void StartCountDown()
    {
        countDownTimer = new CountDownTimer(21000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("2","2");
                if(countdown>9)
                {
                    txt.setText("00:"+String.valueOf(countdown));
                }
                else
                {
                    txt.setText("00:0"+String.valueOf(countdown));
                }
                countdown--;
            }

            @Override
            public void onFinish() {
                countdown = 20;
                countDownTimer.cancel();
                StartCountDown();
                pos++;
                if (pos >= L.size()) {
                    Intent intent = new Intent(Activity_Quiz.this, activity_ketqua.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ",kq);
                    bundle.putInt("Socau",pos);
                    intent.putExtra("MyPackage",bundle);
                    startActivity(intent);
                    countDownTimer.onFinish();
                }
                else {
                    Display(pos); //Hiển thị câu hỏi kế tiếp
                }
            }
        }.start();
    }
    void NextPage()
    {
        countdown=20;
        pos++;
        if (pos >= L.size()) {
            countDownTimer.cancel();;
            Intent intent = new Intent(Activity_Quiz.this,activity_ketqua.class);
            Bundle bundle = new Bundle();
            bundle.putInt("KQ",kq);
            bundle.putInt("Socau",pos);
            intent.putExtra("MyPackage",bundle);
            startActivity(intent);
        }
        else {
            countDownTimer.cancel();
            StartCountDown();
            Display(pos); //Hiển thị câu hỏi kế tiếp
        }
    }

}