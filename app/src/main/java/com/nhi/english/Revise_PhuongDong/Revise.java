package com.nhi.english.Revise_PhuongDong;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.R;
import com.nhi.english.Revise_PhuongDong.Activities.ReviseSpeaking;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//Answer chứa câu trả lời mà user đã chọn

public class Revise extends AppCompatActivity {
    TextView question;
    TextView explain;
    TextView title_;
    RadioGroup rg;
    RadioButton A, B, C, D;
    ImageButton back, next;
    int pos = 0;
    int tmp_pos;
    int result = 0;
    ArrayList<Question> listQuestion = new ArrayList<>();
    ArrayList<Answer> listAnswer = new ArrayList<>();

    ArrayList<Question> listVoca = new ArrayList<>();
    ArrayList<Question> listGram = new ArrayList<>();
    ArrayList<Question> listListen = new ArrayList<>();

    SeekBar mSeekBarTime;
    ImageView image;
    MediaPlayer sound;
//    int countPlaying = 0;
//    Listening listen = new Listening();
    String idSession;

    int idQuestionSpeak = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        title_ = (TextView) findViewById(R.id.title_);

        image = findViewById(R.id.imageView);
        mSeekBarTime = findViewById(R.id.seekBar);
        mSeekBarTime.setVisibility(View.INVISIBLE);

        question = (TextView) findViewById(R.id.question);
        rg = (RadioGroup) findViewById(R.id.RadioGroupAnswer);
        A = (RadioButton) findViewById(R.id.RdbA);
        B = (RadioButton) findViewById(R.id.RdbB);
        C = (RadioButton) findViewById(R.id.RdbC);
        D = (RadioButton) findViewById(R.id.RdbD);
        explain = (TextView) findViewById(R.id.explain);
        explain.setVisibility(View.INVISIBLE);

        back = (ImageButton) findViewById(R.id.ic_back);
        next = (ImageButton) findViewById(R.id.ic_next);

        Intent callerIntent = getIntent();
        try{
            Log.d("WELCOME", "Speaking");
            Bundle packageFormCaller = callerIntent.getBundleExtra("Speaking");
            listQuestion = packageFormCaller.getParcelableArrayList("Question");
            listAnswer = packageFormCaller.getParcelableArrayList("Answer");
            idSession = packageFormCaller.getString("Sound");
            result = Integer.parseInt(packageFormCaller.getString("Result"));
            idQuestionSpeak = packageFormCaller.getInt("IdQuestion speak");
            // gán mảng cho lisListen để phục vụ cho dòng code 278
            for (Question qu: listQuestion){
                if(qu.style.equals("4")){
                    listListen.add(qu);
                }
            }
            for (Question qu: listQuestion){
                if(qu.style.equals("2")){
                    listGram.add(qu);
                }
            }
        }catch(Exception ex){
            Initalize();
        }

        CreatSound(idSession);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ++countPlaying;
//                if (countPlaying <= 2 || sound == null) {
//                    creatSound(idSession);
//                }else{
//                    Toast.makeText(Revise.this, "Listens are over", Toast.LENGTH_LONG).show();
//                }
                if(!sound.isPlaying()){
                    sound.start();
                    image.setImageResource(R.drawable.ic_baseline_pause_24);
                }else{
                    sound.pause();
                    image.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }
            }
        });
        mSeekBarTime.setMax(sound.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mSeekBarTime.setProgress(sound.getCurrentPosition());
            }
        }, 0, 900);
        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                image.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            }
        });
        mSeekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sound.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Display(pos);

        if (pos == 0){
            back.setVisibility(View.INVISIBLE);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tmp_pos = pos; // câu sẽ giảm
                pos--;
                if (pos == 0){
                    back.setVisibility(View.INVISIBLE);
                }
                Display(pos);
                if (listQuestion.get(pos).equals(listGram.get(listGram.size()-1))){
                    sound.stop();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tmp_pos = pos; //câu sẽ tăng
                back.setVisibility(View.VISIBLE);
                int idCheck = rg.getCheckedRadioButtonId();
                CheckAnswer(idCheck, listQuestion);

                Log.d("Button ", String.valueOf(idCheck));
                Log.d("Result ", String.valueOf(result));
                for (Answer i: listAnswer){
                    Log.d("Answer ", i.getIdChecked() + " - " + i.getPos());
                }


                pos++;
                if (pos == listQuestion.size()){
                    sound.stop();
                    Intent intents = new Intent(Revise.this, ReviseSpeaking.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("Question", listQuestion);
                    bundle.putParcelableArrayList("Answer", listAnswer);
                    bundle.putString("Sound", idSession);
                    bundle.putString("Result", String.valueOf(result));
                    bundle.putInt("IdQuestion speak", idQuestionSpeak);
                    intents.putExtra("Revise", bundle);
//                    intents.putExtra("Style", pos);
                    startActivity(intents);
                }else {
                    Display(pos);
                }
            }
        });
    }

    void Display(int i){
        int part = pos + 1;

        switch (listQuestion.get(i).style){
            case "1":
                image.setImageResource(R.drawable.vocabulary);
                mSeekBarTime.setVisibility(View.INVISIBLE);
                title_.setText("Vocabulary");
                break;
            case "2":
                image.setImageResource(R.drawable.grammar);
                mSeekBarTime.setVisibility(View.INVISIBLE);
                title_.setText("Grammar");
                break;
            case "3":
                title_.setText("Reading");
                break;
            case "4":
                if(listQuestion.get(i).equals(listListen.get(0)) && tmp_pos < i){
                    image.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }
                mSeekBarTime.setVisibility(View.VISIBLE);
                title_.setText("Listening");
//                listen.PlaySound(this, image, mSeekBarTime, idSession);
                break;
        }

        question.setText(part + ". " + listQuestion.get(i).content);
        A.setText(listQuestion.get(i).answerA);
        B.setText(listQuestion.get(i).answerB);
        if (listQuestion.get(i).answerC != null){
            C.setVisibility(View.VISIBLE);
            C.setText(listQuestion.get(i).answerC);
        }else{
            C.setVisibility(View.INVISIBLE);
        }
        if(listQuestion.get(i).answerD != null){
            D.setText(listQuestion.get(i).answerD);
        }else{
            D.setVisibility(View.INVISIBLE);
        }

        rg.clearCheck();
        try{
            rg.check(listAnswer.get(pos).getIdChecked());
        }catch (Exception ex){
        }

    }
//
    private void CheckAnswer(int idCheck, ArrayList<Question> listQuestion){
        //Check xem vị trí câu có bị trùng hay không
        if (!checkList(idCheck,pos)){
            listAnswer.add(new Answer(idCheck, pos));
            switch(idCheck){
                case R.id.RdbA:
                    if(listQuestion.get(pos).Answer.compareTo("A") == 0){
                        result += 1;
                        break;
                    }
                case R.id.RdbB:
                    if(listQuestion.get(pos).Answer.compareTo("B") == 0){
                        result += 1;
                        break;
                    }
                case R.id.RdbC:
                    if(listQuestion.get(pos).Answer.compareTo("C") == 0){
                        result += 1;
                        break;
                    }
                case R.id.RdbD:
                    if(listQuestion.get(pos).Answer.compareTo("D") == 0){
                        result += 1;
                        break;
                    }
            }
        }
    }

    Boolean checkList(int a,  int index){
        for (int i = 0; i < listAnswer.size(); i++){
            if(index == listAnswer.get(i).pos){
                if(a != listAnswer.get(i).idChecked){
                    Answer qu = new Answer(a, listAnswer.get(i).pos);
                    listAnswer.set(listAnswer.indexOf(listAnswer.get(i)), qu);
                    //public Object set(int index, Object element)
                }
                return true;
            }
        }
        return false;
    }

    public String RandomSession (){
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(1);
        tmp.add(2);
        tmp.add(3);
        String id = null;
        for (int i = 0; i < tmp.size(); i++) { //random id bài nghe bất kì
            int index = (int) Math.random() * tmp.size();
            id = tmp.get(index).toString();
            break;
        }
        return id;
    }

    private void CreatSound(String idSession) {
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

    private void Initalize(){
        idSession = RandomSession();
        ReadData();
        for (int i = 0; i < listQuestion.size(); i++) {
            switch (listQuestion.get(i).style) {
                case "1":
                    listVoca.add(listQuestion.get(i));
                    break;
                case "2":
                    listGram.add(listQuestion.get(i));
                    break;
                case "4":
                    if (idSession.compareTo(listQuestion.get(i).id) == 0) {
                        listListen.add(listQuestion.get(i));
                    }
                    break;
            }
        }

        Collections.shuffle(listVoca);
        Collections.shuffle(listGram);
        Collections.shuffle(listListen);
        /* tại sao dùng cách này không được
        for (int j = 0; j < list.size(); j++){
            int index = (int)(Math.random() * list.size());
            swapList.add(list.get(index));
        }
        */
        listVoca.addAll(listGram);
        listVoca.addAll(listListen);
        listQuestion = listVoca;
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
                    }
                    String answerD = null;

                    listChild = Item.getElementsByTagName("Answer");
                    String Answer =listChild.item(0).getTextContent();

                    listQuestion.add(new Question(style, id, content, answerA, answerB, answerC, answerD, Answer));
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
}
