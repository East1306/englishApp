package com.nhi.english.Revise_PhuongDong;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ReviseVocabulary extends AppCompatActivity {
    TextView question;
    TextView explain;
    TextView title_;
    RadioGroup rg;
    RadioButton A, B, C, D;
    ImageButton back, next;
    int pos = 0;
    int result = 0;
    ArrayList<Question> list = new ArrayList<>();
    ArrayList<Question> listVoca = new ArrayList<>();
    ArrayList<Question> listGram = new ArrayList<>();
    private ArrayList<String> listAnswer = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        question = (TextView) findViewById(R.id.question);
        explain = (TextView) findViewById(R.id.explain);
        explain.setVisibility(View.INVISIBLE);
        title_ = (TextView) findViewById(R.id.title_);
        rg = (RadioGroup) findViewById(R.id.RadioGroupAnswer);
        A = (RadioButton) findViewById(R.id.RdbA);
        B = (RadioButton) findViewById(R.id.RdbB);
        C = (RadioButton) findViewById(R.id.RdbC);
        D = (RadioButton) findViewById(R.id.RdbD);

        back = (ImageButton) findViewById(R.id.ic_back);
        next = (ImageButton) findViewById(R.id.ic_next);

        ReadData();

        for (int i = 0; i < list.size(); i++){
            switch (list.get(i).style){
                case "1":
                    listVoca.add(list.get(i));
                    break;
                case "2":
                    listGram.add(list.get(i));
                    break;
            }
        }

        Collections.shuffle(listVoca);
        Collections.shuffle(listGram);
        /* tại sao dùng cách này không được
        for (int j = 0; j < list.size(); j++){
            int index = (int)(Math.random() * list.size());
            swapList.add(list.get(index));
        }
        */

        listVoca.addAll(listGram);
        list = listVoca;

        Display(pos);

        if (pos == 0){
            back.setVisibility(View.INVISIBLE);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos--;
                if (pos == 0){
                    back.setVisibility(View.INVISIBLE);
                }
                Display(pos);
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                Toast.makeText(ReviseVocabulary.this, "PLease click next to save answer"
//                        ,Toast.LENGTH_SHORT).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setVisibility(View.VISIBLE);
                int idCheck = rg.getCheckedRadioButtonId();
                CheckAnswer(idCheck, list);
//                switch (list.get(pos).style){
//                    case "1":
//                        CheckAnswer(idCheck, listVoca);
//                        break;
//                    case "2":
//                        CheckAnswer(idCheck, listGram);
//                        break;
//                }
                Log.d("Button:", String.valueOf(idCheck));
                Log.d("Result:", String.valueOf(result));
                pos++;
                if (pos == list.size()){
                    Intent intents = new Intent(ReviseVocabulary.this, ReviseListen.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("kpVoca", result);
                    bundle.putStringArrayList("buttonVoca", listAnswer);
                    intents.putExtra("ReviseVocabulary", bundle);
                    intents.putExtra("Style", pos);
                    startActivity(intents);
                }else {
                    Display(pos);
                }
            }
        });
    }

    void ReadData(){
        try{
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();

            InputStream in = getAssets().open("data_revise.xml");

            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();;
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if (node instanceof Element){
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("Style");
                    String style = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("ID");
                    String id = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String content = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerA");
                    String answerA = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerB");
                    String answerB = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerC");
                    String answerC = null;
                    if(!listChild.item(0).getTextContent().isEmpty()){
                            answerC = listChild.item(0).getTextContent();
                    }else{

                    }

                    String answerD = null;
                    listChild = Item.getElementsByTagName("Answer");
                    String Answer =listChild.item(0).getTextContent();

                    list.add(new Question(style, id, content, answerA, answerB, answerC, answerD, Answer));
//                    Question Q = new Question();
//                    Q.id = id;
//                    Q.content = content;
//                    Q.answerA = answerA;
//                    Q.answerB = answerB;
//                    Q.answerC = answerC;
//                    Q.Answer = Answer;
//                    list.add(Q);
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
        int part = pos + 1;

        switch (list.get(i).style){
            case "1":
                title_.setText("Vocabulary");
                break;
            case "2":
                title_.setText("Grammar");
                break;
        }
        question.setText(part + ". " + list.get(i).content);
        A.setText(list.get(i).answerA);
        B.setText(list.get(i).answerB);
        if (list.get(i).answerC != null){
            C.setText(list.get(i).answerC);
        } else{
            C.setVisibility(View.INVISIBLE);
        }
        if(list.get(i).answerD != null){
            D.setText(list.get(i).answerD);
        }else{
            D.setVisibility(View.INVISIBLE);
        }
        rg.clearCheck();
        try {
            rg.check(Integer.parseInt(listAnswer.get(i)));
        }catch (Exception ex){
            rg.clearCheck();
        }
    }
//
    private void CheckAnswer(int idCheck, ArrayList<Question> listQuestion){
        switch(idCheck){
            case R.id.RdbA:
                if(listQuestion.get(pos).Answer.compareTo("A") == 0){
                    result += 1;
                    break;
                }
            case R.id.RdbB:
                if(listQuestion.get(pos).Answer.compareTo("A") == 0){
                    result += 1;
                    break;
                }
            case R.id.RdbC:
                if(listQuestion.get(pos).Answer.compareTo("A") == 0){
                    result += 1;
                    break;
                }
            case R.id.RdbD:
                if(listQuestion.get(pos).Answer.compareTo("A") == 0){
                    result += 1;
                    break;
                }
        }
        if (!checkList(String.valueOf(idCheck),pos)){
            listAnswer.add(String.valueOf(idCheck));
        }
    }

    Boolean checkList(String a,  int index){
        for (int i = 0; i < listAnswer.size(); i++){
            if(index == listAnswer.indexOf(listAnswer.get(i))){
                if(a.compareTo(listAnswer.get(i))!= 0){
                    listAnswer.set(listAnswer.indexOf(listAnswer.get(i)), a);
                }
                return true;
            }
        }
        return false;
    }
}
