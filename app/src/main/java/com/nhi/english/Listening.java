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
    Button back;
    int pos = 0;
    ArrayList<QuestionNare> L = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listen_audio);
        Cauhoi = (TextView) findViewById(R.id.T1);
        Noidung = (TextView) findViewById(R.id.T2);
        back = findViewById(R.id.BtnBack);
        ReadData();
        Display(pos);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(Listening.this, MenuActivity.class);
            startActivity(intent);
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
            Toast.makeText(this,"MediaaPlayer released",Toast.LENGTH_SHORT).show();
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
//Tạo đối tượng DocumentBuilder (builder )
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
//Tạo FileInputStream từ tập tin XML nguồn
            InputStream in = getAssets().open("listen_data.xml");
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
                    NodeList listChild = Item.getElementsByTagName("Title");
//lấy nội dung của tag ID
//lấy nội dung của tag ID
                    String Title = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Text");
//lấy nội dung của tag ID
                    String Text = listChild.item(0).getTextContent();
//lưu vào list
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