//package com.nhi.english;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//public class Activity_layout_correctword extends AppCompatActivity {
//
////    Correct_Word{
////
////    }
//    Random random;
//
//    TextView Question;
//    EditText Answer;
//    Button btnAnswer,btnSkip;
//    int pos = 0;
//    ArrayList<QuestionNare> L = new ArrayList();
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle saveInstanceState) {
//
//
//        super.onCreate(saveInstanceState);
//        setContentView(R.layout.layout_correctword);
//
//        Question = findViewById(R.id.txtQuestion);
//        Answer = findViewById(R.id.InputAnswer);
//        btnAnswer = findViewById(R.id.btnAnswer);
//        btnSkip = findViewById(R.id.btnSkip);
//
//        ReadData();
//        Collections.shuffle(L);
//        Display(pos);
//
//        random = new Random();
//
//        Question.setText(mixWords());
//
//        btnAnswer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pos++;
//                if(Answer.getText().toString().equalsIgnoreCase()){
//                    Toast.makeText(Activity_layout_correctword.this, "You are correct:)",Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(Activity_layout_correctword.this, "You are Wrong:)",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        btnSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Question.setText(mixWords());
//
//            }
//        });
//    }
//
//
//    private String mixWords(String word){
//
//        List<String> words = Arrays.asList(word.split( ""));
//
//        Collections.shuffle(words);
//        String mixed = "";
//
//        for(String i : words){
//
//            mixed += i;
//        }
//
//        return mixed;
//    }
//
//    void Display(int i){
//        Question.setText(L.get(i).);
//    }
//
//    void ReadData() {
//        try {
//
//            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = DBF.newDocumentBuilder();
//
//            InputStream in = getAssets().open("correct_words_data.xml");
//
//            Document doc = builder.parse(in);
//            Element root = doc.getDocumentElement();
//            NodeList list = root.getChildNodes();
//            for (int i = 0; i < list.getLength(); i++) {
//
//                Node node = list.item(i);
//
//                if (node instanceof Element) {
//                    Element Item = (Element) node;
//
//                    NodeList listChild = Item.getElementsByTagName("ID");
//
//                    String Title = listChild.item(0).getTextContent();
//                    listChild = Item.getElementsByTagName("Question");
//
//                    String Text = listChild.item(0).getTextContent();
//
//                    QuestionNare Q1 = new QuestionNare();
//
//                    Q1.Question=Question;
//                    L.add(Q1);
//                };
//            }
//
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
