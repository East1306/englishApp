package com.nhi.english;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class QuestionNare {
    public String ID,title,text;
    public String Q;
    public String AnswerA, AnswerB, AnswerC, AnswerD, Answer;
}
public class Activity_Quiz extends AppCompatActivity {
    TextView txt;
    String TAG = "Main";
    TextView Cauhoi,Ketqua;
    RadioGroup RG;
    Button BT;
    RadioButton A,B,C,D;
    int pos=0;//vị trí câu hỏi trong danh sách
    int kq=0; //lưu số câu trả lời đúng
    int HighScore = 0;
    ArrayList <QuestionNare> L ; //chứa câu hỏi
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gramma_vocab);
        L = new ArrayList<>();
        Cauhoi = (TextView) findViewById(R.id.TxtCauhoi);
        Ketqua = (TextView)findViewById(R.id.TxtKetqua);
        RG = (RadioGroup)findViewById(R.id.RadioGroupTraloi);
        BT = (Button) findViewById(R.id.BtnTraloi);
        A = (RadioButton)findViewById(R.id.RdbA);
        B = (RadioButton)findViewById(R.id.RdbB);
        C = (RadioButton)findViewById(R.id.RdbC);
        D = (RadioButton)findViewById(R.id.RdbD);
        ReadData();
        Display(pos);
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idCheck = RG.getCheckedRadioButtonId();
                switch (idCheck) {
                    case R.id.RdbA:
//Nếu đáp án là câu A thì cộng kq lên 1
                        if (L.get(pos).Answer.compareTo("A")==0) kq = kq+1;
                        break;
                    case R.id.RdbB:
//Nếu đáp án là câu B thì cộng kq lên 1
                        if (L.get(pos).Answer.compareTo("B")==0) kq = kq+1;
                        break;
                    case R.id.RdbC:
//Nếu đáp án là câu C thì cộng kq lên 1
                        if (L.get(pos).Answer.compareTo("C")==0) kq = kq+1;
                        break;
                    case R.id.RdbD:
//Nếu đáp án là câu D thì cộng kq lên 1
                        if (L.get(pos).Answer.compareTo("D")==0) kq = kq+1;
                        break;
                }
                pos++; //Xong 1 câu thì tăng pos lên 1 để làm câu kế tiếp
//Nếu trả lời hết câu hỏi
                if (pos >= L.size()) {
                    Intent intent = new Intent(Activity_Quiz.this, activity_ketqua.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ", kq);
                    bundle.putInt("Socau", pos);
                    intent.putExtra("MyPackage", bundle);
                    startActivity(intent);
                    pos =0; //Cho vị trí pos về câu hỏi đầu tiên
                    kq =0; //cho số câu hỏi đúng bằng 0, để làm lại
                    Display(pos); // Hiển thị lại nội dung
                    if (kq > HighScore) {
                        HighScore = kq;
                        SaveHighScore();
                    }
                    finish();
                }
                else Display(pos); //Hiển thị câu hỏi kế tiếp
            }
        });
        txt = findViewById(R.id.txttime);
        Intent intent = new Intent(this, BroadcastService_Quiz.class);
        startService(intent);
        Log.i(TAG, "Started Service");
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
        registerReceiver(broadcastReceiver, new IntentFilter(BroadcastService_Quiz.COUNTDOWN_CDT));
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
        stopService(new Intent(this, BroadcastService_Quiz.class));
        Log.i(TAG, "Stopped service");
        super.onDestroy();
    }

    private void updateGUI(Intent intent){
        if (intent.getExtras() != null){
            long millisUntilFinished = intent.getLongExtra("countdown", 20000);
            Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished/1000);

            txt.setText(Long.toString(millisUntilFinished / 1000));
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
            sharedPreferences.edit().putLong("time", millisUntilFinished).apply();
        }
    }
    //Hiển thị nội dung+
    void Display(int i){
        Cauhoi.setText(L.get(i).Q);
        A.setText(L.get(i).AnswerA);
        B.setText(L.get(i).AnswerB);
        C.setText(L.get(i).AnswerC);
        D.setText(L.get(i).AnswerD);
        Ketqua.setText("Câu đúng:" + kq);
        RG.clearCheck(); //xóa checked
    }
    void ReadData() {
        try {
            Log.e("","size");
//Tạo đối tượng DocumentBuilder (builder )
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
//Tạo FileInputStream từ tập tin XML nguồn
            InputStream in = getAssets().open("quiz_data.xml");
//Dùng phương thức parse của đối tượng builder để tạo Document
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();//lấy tag Root
            NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
            for (int i = 0; i < list.getLength(); i++) {
// duyệt từ node đầu tiên cho tới node cuối cùng
                Node node = list.item(i);// mỗi lần duyệt thì lấy ra 1 node
// kiểm tra xem node đó có phải là Element hay không
                if (node instanceof Element) {
                    Element Item = (Element) node;// lấy được tag Item
// lấy tag ID bên trong của tag Item
                    NodeList listChild = Item.getElementsByTagName("ID");
//lấy nội dung của tag ID
                    String ID = listChild.item(0).getTextContent();
// lấy tag Question
                    listChild = Item.getElementsByTagName("Question");
// lấy nội dung Question
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
//lưu vào list
                    QuestionNare Q1 = new QuestionNare();
                    Q1.ID = ID;
                    Q1.Q = Question;
                    Q1.AnswerA = AnswerA;
                    Q1.AnswerB = AnswerB;
                    Q1.AnswerC = AnswerC;
                    Q1.AnswerD = AnswerD;
                    Q1.Answer = Answer;
                    L.add(Q1);
                    Log.e("","size"+L.size());
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
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("H",HighScore);
        editor.apply();
    }

}