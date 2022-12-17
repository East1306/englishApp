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
import java.util.Collections;

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
        Collections.shuffle(L);
        Display(pos);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos--;
                if (pos < 0){
                    Intent intent = new Intent(Listening.this, Activity_HomeMenu.class);
                    startService(intent);
                }
                stopPlayer();
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
                else {
                    stopPlayer();
                    Display(pos); //Hiển thị câu hỏi kế tiếp
                }
            }
        });
    }

    public void play(View v){

        if (player == null) {
            switch (L.get(pos).title){
                case"Yoda-the cat with four ears":
                    player = MediaPlayer.create(this, R.raw.yoda_the_cat__with_four_ears);
                    break;
                case"Salt coffee – Last Part":
                    player = MediaPlayer.create(this, R.raw.salt_coffee_last_part);
                    break;
                case"Dating"  :
                    player = MediaPlayer.create(this, R.raw.dating);
                    break;
                case"Reasons of love":
                    player = MediaPlayer.create(this,R.raw.reasons_of_love);
                    break;
                case"The North Sea Protection Works":
                    player = MediaPlayer.create(this,R.raw.the_north_sea_protection_works);
                    break;
                case"Love Map":
                    player = MediaPlayer.create(this,R.raw.love_map);
                    break;
                case"The Panama Canal":
                    player = MediaPlayer.create(this,R.raw.the_panama_canal);
                    break;
                case"The Empire State Building":
                    player = MediaPlayer.create(this,R.raw.the_empire_state_building);
                    break;
                case"The Itaipu Dam":
                    player = MediaPlayer.create(this,R.raw.the_itaipu_dam);
                    break;
                case"Young children play sports":
                    player = MediaPlayer.create(this,R.raw.young_children_play_sports__advantages_and_disadvantages);
                    break;
            }
            player.start();
            Toast.makeText(this,"MediaPlayer is playing",Toast.LENGTH_SHORT).show();
        }
        else{
            player.start();
            Toast.makeText(this,"MediaPlayer is playing",Toast.LENGTH_SHORT).show();
        }
    }

    public void pause(View v){
        if(player != null){
            player.pause();
            Toast.makeText(this,"MediaPlayer has paused",Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View v){
        stopPlayer();
    }

    private void stopPlayer(){
        if(player !=null){
            player.stop();
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