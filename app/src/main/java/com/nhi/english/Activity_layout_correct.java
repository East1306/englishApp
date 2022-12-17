package com.nhi.english;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


class Correct {
    public String ID;
    public String Q;
}
public class Activity_layout_correct extends AppCompatActivity {

    TextView Question;
    EditText Answer;
    Button btnAnswer,btnSkip;
    int pos = 0;
    ArrayList<Correct> L = new ArrayList();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_correctword);
        Question =(TextView) findViewById(R.id.txtQuestion);
        Answer = (EditText) findViewById(R.id.InputAnswer);
        btnAnswer =(Button) findViewById(R.id.btnAnswer);
        btnSkip =(Button) findViewById(R.id.btnSkip);
        ReadData();
        Collections.shuffle(L);
        Display(pos);

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Answer.getText().toString().equalsIgnoreCase(L.get(pos).Q)){
                    Toast.makeText(Activity_layout_correct.this, "You are correct:)",Toast.LENGTH_SHORT).show();
                    pos++;
                    Display(pos);
                }
                else{
                    Toast.makeText(Activity_layout_correct.this, "You are Wrong:)",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos++;
                Display(pos);
            }
        });
    }

    private String mixWords(String word){

        List<String> words = Arrays.asList(word.split(""));
        Collections.shuffle(words);
        String mixed = "";
        for(String i : words){
            mixed += i;
        }
        return mixed;
    }

    void Display(int i){
        Answer.setText(" ");
        Question.setText(mixWords(L.get(i).Q).toLowerCase(Locale.ROOT));
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
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    Correct Q1 = new Correct();
                    Q1.ID = ID;
                    Q1.Q = Question;
                    L.add(Q1);
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
}
