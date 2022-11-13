package com.nhi.english.Revise_PhuongDong;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ReviseVocabulary extends AppCompatActivity {
    TextView question;
    TextView explain;
    RadioGroup rg;
    RadioButton A, B, C, D;
    ImageButton back, next;
    int pos = 0;
    int result = 0;
    ArrayList<Question> list = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        question = (TextView) findViewById(R.id.question);
        explain = (TextView) findViewById(R.id.explain);
        rg = (RadioGroup) findViewById(R.id.RadioGroupAnswer);
        A = (RadioButton) findViewById(R.id.RdbA);
        B = (RadioButton) findViewById(R.id.RdbB);
        C = (RadioButton) findViewById(R.id.RdbC);
        D = (RadioButton) findViewById(R.id.RdbD);
        back = (ImageButton) findViewById(R.id.ic_back);
        next = (ImageButton) findViewById(R.id.ic_next);

        ReadData();
        Display(pos);

        int idCheck = rg.getCheckedRadioButtonId();
        switch (idCheck){
            case R.id.RdbA:
                if(list.get(pos).Answer.compareTo("A") == 0) result += 1;
            case R.id.RdbB:
                if(list.get(pos).Answer.compareTo("B") == 0) result += 1;
            case R.id.RdbC:
                if(list.get(pos).Answer.compareTo("C") == 0) result += 1;
            case R.id.RdbD:
                if(list.get(pos).Answer.compareTo("D") == 0) result += 1;
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos--;
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos++;
            }
        });
    }

    void ReadData(){
        try{
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();

            InputStream in = getAssets().open("data_reviseVoca.xml");

            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();;
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if (node instanceof Element){
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String id = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String content = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerA");
                    String answerA = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerB");
                    String answerB = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerC");
                    String answerC = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Answer");
                    String Answer =listChild.item(0).getTextContent();

                    Question Q = new Question();
                    Q.id = id;
                    Q.content = content;
                    Q.answerA = answerA;
                    Q.answerB = answerB;
                    Q.answerC = answerC;
                    Q.Answer = Answer;
                    list.add(Q);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    void Display(int i){
        question.setText(list.get(i).content);
        A.setText(list.get(i).answerA);
        B.setText(list.get(i).answerB);
        C.setText(list.get(i).answerC);
        D.setText(list.get(i).answerD);
        rg.clearCheck();
    }
}

//package com.nhi.english;
//
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.RadioButton;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//
//class Question{
//    int id;
//    String content;
//    String answerA, answerB, answerC, answerD;
//}
//
//public class ReviseVocabulary extends AppCompatActivity {
//    TextView question;
//    TextView explain;
//    RadioButton A, B, C, D;
//    ImageButton back, next;
//    int pos = 0;
//    ArrayList<Question> list = new ArrayList<>();
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.Vocabulary);
//
//        question = (TextView) findViewById(R.id.question);
//        explain = (TextView) findViewById(R.id.explain);
//        A = (RadioButton) findViewById(R.id.RdbA);
//        B = (RadioButton) findViewById(R.id.RdbB);
//        C = (RadioButton) findViewById(R.id.RdbC);
//        D = (RadioButton) findViewById(R.id.RdbD);
//        back = (ImageButton) findViewById(R.id.ic_back);
//        next = (ImageButton) findViewById(R.id.ic_next);
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        ReadData();
//        Display();
//
//
//    }
//
//    void ReadData(){
//    }
//    void Display(){
//    }
//}
