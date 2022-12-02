package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


public class Listening extends AppCompatActivity {
    MediaPlayer player;
    TextView Cauhoi,Noidung;
    Button back,next;
    int pos = 0;
    ArrayList<QuestionNare> L = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listen_audio);
        Cauhoi = (TextView) findViewById(R.id.T1);
        Noidung = (TextView) findViewById(R.id.T2);
        back = findViewById(R.id.BtnBack);
        next = findViewById(R.id.BtnNext);
        ReadData();
        Display(pos);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setVisibility(View.VISIBLE);
                pos++;
                if (pos >= L.size()) {
                    finish();
                }
                else Display(pos); //Hiển thị câu hỏi kế tiếp
            }
        });
    }


    public void play(View v){
        if (player == null){
            player = MediaPlayer.create(this,R.raw.yoda_the_cat__with_four_ears);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    public void pause(View v){
        if(player != null){
            player.pause();
        }
    }

    public void stop(View v){
        stopPlayer();
    }
    private void stopPlayer(){
        if(player !=null){
            player.release();
            player = null;
            Toast.makeText(this,"MediaPlayer has stopped",Toast.LENGTH_SHORT).show();
        }
    }
    protected void onStop(){
        super.onStop();
        stopPlayer();
    }

    void Display(int i){
        Cauhoi.setText(L.get(i).title);
        Noidung.setText(L.get(i).text);
    }
    void ReadData() {
        try {

            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();

            InputStream in = getAssets().open("listen_data.xml");

            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node instanceof Element) {
                    Element Item = (Element) node;

                    NodeList listChild = Item.getElementsByTagName("Title");

                    String Title = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Text");

                    String Text = listChild.item(0).getTextContent();

                    QuestionNare Q1 = new QuestionNare();

                    Q1.title=Title;
                    Q1.text=Text;
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