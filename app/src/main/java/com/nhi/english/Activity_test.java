package com.nhi.english;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import android.app.Activity;
class QuestionNare {
    public String title,text;
}
public class Activity_test extends Activity {
    TextView Cauhoi;
    RadioGroup RG;
    Button BT;
    RadioButton A,B,C,D;
    int pos=0;//vị trí câu hỏi trong danh sách
    int kq=0; //lưu số câu trả lời đúng
    ArrayList<QuestionNare> L = new ArrayList(); //chứa câu hỏi
    int HighScore = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gramma_vocab);
        Cauhoi = (TextView) findViewById(R.id.TxtCauhoi);
        RG = (RadioGroup)findViewById(R.id.radioGroup);
        BT = (Button) findViewById(R.id.BtnTraloi);
        A = (RadioButton)findViewById(R.id.rb1);
        B = (RadioButton)findViewById(R.id.rb2);
        C = (RadioButton)findViewById(R.id.rb3);
        D = (RadioButton)findViewById(R.id.rb4);
//        LoadHighScore();
//        ReadData();
//        Display(pos);
//        BT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int idCheck = RG.getCheckedRadioButtonId();
//                switch (idCheck) {
//                    case R.id.RdbA:
////Nếu đáp án là câu A thì cộng kq lên 1
//                        if (L.get(pos).Answer.compareTo("A")==0) kq = kq+1;
//                        break;
//                    case R.id.RdbB:
////Nếu đáp án là câu B thì cộng kq lên 1
//                        if (L.get(pos).Answer.compareTo("B")==0) kq = kq+1;
//                        break;
//                    case R.id.RdbC:
////Nếu đáp án là câu C thì cộng kq lên 1
//                        if (L.get(pos).Answer.compareTo("C")==0) kq = kq+1;
//                        break;
//                    case R.id.RdbD:
////Nếu đáp án là câu D thì cộng kq lên 1
//                        if (L.get(pos).Answer.compareTo("D")==0) kq = kq+1;
//                        break;
//                }
//                pos++;
////Nếu trả lời hết câu hỏi
//                if (pos >= L.size()) {
//                    Intent intent = new Intent(Activity_test.this, activity_ketqua.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("KQ", kq);
//                    bundle.putInt("Socau", pos);
//                    intent.putExtra("MyPackage", bundle);
//                    startActivity(intent);
//                    if (kq > HighScore) {
//                        HighScore = kq;
//                        SaveHighScore();
//                    }
//                    finish();
//                }
//                else Display(pos); //Hiển thị câu hỏi kế tiếp
//            }
//        });
//    }
//    //Hiển thị nội dung
//    void Display(int i){
//        Cauhoi.setText(L.get(i).Q);
//        A.setText(L.get(i).AnswerA);
//        B.setText(L.get(i).AnswerB);
//        C.setText(L.get(i).AnswerC);
//        D.setText(L.get(i).AnswerD);
//        RG.clearCheck(); //xóa checked
//    }
//    void ReadData() {
//        try {
////Tạo đối tượng DocumentBuilder (builder )
//            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = DBF.newDocumentBuilder();
////Tạo FileInputStream từ tập tin XML nguồn
//            InputStream in = getAssets().open("data.xml");
////Dùng phương thức parse của đối tượng builder để tạo Document
//            Document doc = builder.parse(in);
//            Element root = doc.getDocumentElement();//lấy tag Root
//            NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
//            for (int i = 0; i < list.getLength(); i++) {// duyệt từ node đầu tiên cho tới node cuối cùng
//                Node node = list.item(i);// mỗi lần duyệt thì lấy ra 1 node
//// kiểm tra xem node đó có phải là Element hay không
//                if (node instanceof Element) {
//                    Element Item = (Element) node;// lấy được tag Item
//// lấy tag ID bên trong của tag Item
//                    NodeList listChild = Item.getElementsByTagName("ID");
//                    String ID = listChild.item(0).getTextContent();//lấy nội dung của tag ID
//                    listChild = Item.getElementsByTagName("Question");// lấy tag Question
//                    String Question = listChild.item(0).getTextContent();//lấy nội dung Question
//                    listChild = Item.getElementsByTagName("AnswerA");
//                    String AnswerA = listChild.item(0).getTextContent();
//                    listChild = Item.getElementsByTagName("AnswerB");
//                    String AnswerB = listChild.item(0).getTextContent();
//                    listChild = Item.getElementsByTagName("AnswerC");
//                    String AnswerC = listChild.item(0).getTextContent();
//                    listChild = Item.getElementsByTagName("AnswerD");
//                    String AnswerD = listChild.item(0).getTextContent();
//                    listChild = Item.getElementsByTagName("Answer");
//                    String Answer = listChild.item(0).getTextContent();
////lưu vào list
//                    QuestionNare Q1 = new QuestionNare();
//                    Q1.ID = ID;
//                    Q1.Q = Question;
//                    Q1.AnswerA = AnswerA;
//                    Q1.AnswerB = AnswerB;
//                    Q1.AnswerC = AnswerC;
//                    Q1.AnswerD = AnswerD;
//                    Q1.Answer = Answer;
//                    L.add(Q1);
//                };
//            }
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    void LoadHighScore(){
//        SharedPreferences sharedPreferences = getSharedPreferences("MyData",
//                Context.MODE_PRIVATE);
//        if (sharedPreferences !=null){
//            HighScore = sharedPreferences.getInt("H",0);
//        }
//    }
//    void SaveHighScore(){
//        SharedPreferences sharedPreferences = getSharedPreferences("MyData",Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("H",HighScore);
//        editor.apply();
    }
}
