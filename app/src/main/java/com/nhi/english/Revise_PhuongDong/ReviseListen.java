package com.nhi.english.Revise_PhuongDong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Handler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ReviseListen extends AppCompatActivity {
    ImageButton playSound, stopSound, pauseSound;
    ImageButton next, back;
    TextView time, question, explain;
    RadioButton A, B, C, D;
    SeekBar mSeekBarTime;

    int pos = 0;
    String idSession;
    ArrayList<Integer> tmp = new ArrayList<>();
    ArrayList<Question> list_ = new ArrayList<>();
    MediaPlayer sound;

    //    ArrayList<String> sessions = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listening);

        playSound = (ImageButton) findViewById(R.id.buttonPlaySound);
        stopSound = (ImageButton) findViewById(R.id.buttonStopSound);
        pauseSound = (ImageButton) findViewById(R.id.buttonPauseSound);
        next = (ImageButton) findViewById(R.id.ic_next);
        back = (ImageButton) findViewById(R.id.ic_back);
        time = (TextView) findViewById(R.id.timePlaySound);
        explain = (TextView) findViewById(R.id.explain);
        question = (TextView) findViewById(R.id.contentListen);
        A = (RadioButton) findViewById(R.id.RdbA);
        B = (RadioButton) findViewById(R.id.RdbB);
        C = (RadioButton) findViewById(R.id.RdbC);
        D = (RadioButton) findViewById(R.id.RdbD);

        // mảng tmp chứa thứ tự Id theo bài nghe
        tmp.add(1);
        tmp.add(2);
        tmp.add(3);

        for (int i = 0; i < tmp.size(); i++) { //random id bài nghe bất kì
            int index = (int) Math.random() * tmp.size();
            idSession = tmp.get(index).toString();
            break;
        }

        ReadData();
        Collections.shuffle(list_);

//        Intent get = getIntent();
//        pos = get.getExtras().getInt("Style");
        Display(pos);

        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sound == null) {
                    creatSound(idSession);
                }
                sound.start();
            }
        });

        pauseSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.pause();
            }
        });

        stopSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.release();
                sound = null;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++pos;
                if (pos >= list_.size()) {
                    sound.stop();
                    Intent intent = new Intent(ReviseListen.this, ReviseSpeaking.class);
                    startActivity(intent);
                } else {
                    Display(pos);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --pos;
                if (pos < list_.size()) {
                    sound.stop();
                    Intent intent = new Intent(ReviseListen.this, ReviseVocabulary.class);
                    startActivity(intent);
                } else {
                    Display(pos);
                }
            }
        });
    }

    private void creatSound(String idSession) {
        switch (idSession) {
            case "1":
                sound = MediaPlayer.create(this, R.raw.multiplechoicel_practice1a);
                break;
            case "2":
                sound = MediaPlayer.create(this, R.raw.multiplechoicel_practice2a);
                break;
            case "3":
                sound = MediaPlayer.create(this, R.raw.multiplechoicel_practice3a);
        }
    }

    void ReadData() {
//        Intent intent = getIntent();
//        String style = intent.getStringExtra("Style");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            InputStream in = getAssets().open("data_revise.xml");
            // tạo document bằng phương thức parse từ đối tượng builder
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node instanceof Element) {
                    Element data = (Element) node;
                    NodeList listChild;
                    listChild = data.getElementsByTagName("Style");
                    String style = listChild.item(0).getTextContent();
                    listChild = data.getElementsByTagName("ID");
                    String id = listChild.item(0).getTextContent();
                    listChild = data.getElementsByTagName("Question");
                    String question = listChild.item(0).getTextContent();
                    listChild = data.getElementsByTagName("AnswerA");
                    String answerA = listChild.item(0).getTextContent();
                    listChild = data.getElementsByTagName("AnswerB");
                    String answerB = listChild.item(0).getTextContent();
                    listChild = data.getElementsByTagName("AnswerC");
                    String answerC = listChild.item(0).getTextContent();
                    String answerD = null;
                    listChild = data.getElementsByTagName("Answer");
                    String answer = listChild.item(0).getTextContent();
//
                    if (style.equals("4") && id.equals(idSession)) {
                        Question question_ = new Question(style, id, question, answerA, answerB, answerC, answerD, answer);
                        list_.add(question_);
                    }
                    if (style.compareTo("4") == 1) { //style > 4
                        break;
                    }
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

    void Display(int i) {
        if (list_ != null && list_.size() != 0) {
            question.setText((pos + 1) + ". " + list_.get(i).content);
            A.setText(list_.get(i).answerA);
            B.setText(list_.get(i).answerB);
            C.setText(list_.get(i).answerC);
//            if (list_.get(i).answerC != null){
//                C.setText(list_.get(i).answerC);
//            } else{
//                C.setVisibility(View.INVISIBLE);
//            }
            if (list_.get(i).answerD != null) {
                D.setText(list_.get(i).answerD);
            } else {
                D.setVisibility(View.INVISIBLE);
            }
        }
    }

//     sound.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
//
//    {
//        @Override
//        public void onPrepared (MediaPlayer mp){
//        mSeekBarTime.setMax(sound.getDuration());
//        sound.start();
//    }
//    });
//
//        mSeekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//
//    {
//        @Override
//        public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
//        if (fromUser) {
//            sound.seekTo(progress);
//            mSeekBarTime.setProgress(progress);
//        }
//    }
//
//        @Override
//        public void onStartTrackingTouch (SeekBar seekBar){
//
//    }
//
//        @Override
//        public void onStopTrackingTouch (SeekBar seekBar){
//
//    }
//    });
//
//        new
//
//    Thread(new Runnable() {
//        @Override
//        public void run () {
//            while (sound != null) {
//                try {
//                    if (sound.isPlaying()) {
//                        Message message = new Message();
//                        message.what = sound.getCurrentPosition();
//                        handler.sendMessage(message);
//                        Thread.sleep(1000);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    })
//
//    start();
//
//    @SuppressLint("Handler Leak")
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            mSeekBarTime.setProgress(msg.what);
//        }
//    };
//}
}
