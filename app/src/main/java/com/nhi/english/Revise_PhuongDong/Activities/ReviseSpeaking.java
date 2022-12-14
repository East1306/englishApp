package com.nhi.english.Revise_PhuongDong.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContextWrapper;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhi.english.R;
import com.nhi.english.Revise_PhuongDong.Answer;
import com.nhi.english.Revise_PhuongDong.Fragment.RecordFragment;
import com.nhi.english.Revise_PhuongDong.Question;
import com.nhi.english.Revise_PhuongDong.Result;
import com.nhi.english.Revise_PhuongDong.Revise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class ReviseSpeaking extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent showContent = new Intent(getApplicationContext(),
                RecordFragment.class);
        startActivity(showContent);
    }
}
//    private static int MICROPHONE_PERMISSION_CODE = 200;
////    MediaRecorder mediaRecorder;
////    MediaPlayer mediaPlayer;
////    FloatingActionButton record;
//
//    MediaRecorder mediaRecorder;
//    long mStartingTimeMillis = 0;
//    long mElapasedMillis = 0;
//
//    File file;
//
//    String fileName;
//
//    //    DBHelper dbHelper;
//    ImageButton back;
//    Button submit, voice;
//
//    TextView content;
//    ProgressBar timeRecord;
//    //Gi??? nh???ng c??u h???i _ c??u tr??? l???i t??? class Revise qua
//    ArrayList<Question> listQuestion = new ArrayList<>();
//    ArrayList<Answer> listAnswer = new ArrayList<>();
//    String sound, result;
//    private ArrayList<String> list_question = new ArrayList<>();
//    int valueRandom;
//
//    final AlertDialog.Builder adb = new AlertDialog.Builder(this);
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.speaking);
////        dbHelper = new DBHelper(getApplicationContext());
////        record = (FloatingActionButton) findViewById(R.id.buttonRecord);
////        timeRecord = findViewById(R.id.recordProgressBar);
//        submit = (Button) findViewById(R.id.buttonSubmit);
//        back = (ImageButton) findViewById(R.id.ic_back);
//        content = findViewById(R.id.contentSpeaking);
////        voice = findViewById(R.id.btnRecord);
//        Intent callerIntent = getIntent();
//        //L???y Bundle d???a v??o Revise
//        Bundle packageFormCaller= callerIntent.getBundleExtra("Revise");
//        listQuestion = packageFormCaller.getParcelableArrayList("Question");
//        listAnswer = packageFormCaller.getParcelableArrayList("Answer");
//        sound = packageFormCaller.getString("Sound");
//        result = packageFormCaller.getString("Result");
//        valueRandom = packageFormCaller.getInt("IdQuestion speak");
//
//        ReadData();
//        if(valueRandom == 0){ //T??? revise chy???n qua
//            valueRandom = (int)(Math.random() * list_question.size());
//        }
//
//        Display(valueRandom);
//        Log.d("ID Session", sound);
//        for (Question i: listQuestion){
//            Log.d("Question", i.content);
//        }
//
//        if(isMicrophonePresent()){
//            getMicrophonePermission();
//        }
//
//        voice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Long tsLong = System.currentTimeMillis()/1000;
//                String ts = tsLong.toString();
//
//                fileName = "audio_" + ts;
//
//                file = new File(Environment.getExternalStorageDirectory()
//                        + "/MySoundRecord" + fileName + ".mp3");
//                mediaRecorder = new MediaRecorder();
//                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//                mediaRecorder.setOutputFile(file.getAbsolutePath());
//                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
//                mediaRecorder.setAudioChannels(1);
//
//                try {
//                    mediaRecorder.prepare();
//                    mediaRecorder.start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                mStartingTimeMillis = System.currentTimeMillis();
//
//                if(mediaRecorder != null){
//                    stopRecording();
//                }
//            }
//        });
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ReviseSpeaking.this,
//                        Revise.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("Question", listQuestion);
//                bundle.putParcelableArrayList("Answer", listAnswer);
//                bundle.putString("Sound", sound);
//                bundle.putString("Result", result);
//                bundle.putInt("IdQuestion speak", valueRandom);
//                intent.putExtra("Speaking", bundle);
//                startActivity(intent);
//            }
//        });
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                adb.setTitle("Confirm?");
//                adb.setMessage("Plese Confirm");
//                adb.setNegativeButton("Cancel", null);
//                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int arg1) {
//                        // TODO Auto-generated method stub
//                        Toast.makeText(ReviseSpeaking.this,"Your Click OK. " ,
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//                adb.show();
//
//                Intent intent = new Intent(ReviseSpeaking.this, Result.class);
//                intent.putExtra("Result", result);
//                intent.putExtra("Total sentense", String.valueOf(listQuestion.size()));
//                startActivity(intent);
//            }
//        });
//
//    }
//
//    private boolean isMicrophonePresent (){
//        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    private void getMicrophonePermission() {
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
//                == PackageManager.PERMISSION_DENIED) {
//            ActivityCompat.requestPermissions(this, new String[]
//                    {Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
//        }
//    }
//
//    private String getFilePath(){
//        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
//        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
//        File file = new File(musicDirectory, "recordingAudio" + ".mp3");
//        return file.getPath();
//    }
//
//    private void ReadData() {
//        try{
//            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = DBF.newDocumentBuilder();
//
//            InputStream in = getAssets().open("data_reviseSpeak.xml");
//
//            Document doc = builder.parse(in);
//            Element root = doc.getDocumentElement();
//
//            NodeList nodeList = root.getChildNodes();
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Node node = nodeList.item(i);
//                if (node instanceof Element) {
//                    Element Item = (Element) node;
//                    NodeList listChild = Item.getElementsByTagName("Content");
//                    String content = listChild.item(0).getTextContent();
//                    list_question.add(content);
//                }
//            }
//        }catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void Display(int i){
//        content.setText(list_question.get(i));
//    }
//
//
//    private void stopRecording(){
//        mediaRecorder.stop();
//        mElapasedMillis = System.currentTimeMillis() - mStartingTimeMillis;
//        mediaRecorder.release();
//        Toast.makeText(getApplicationContext(), "Recording saved "
//                + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//        //add to database
//
//    }

//        record.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    mediaRecorder = new MediaRecorder();
//                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//                    mediaRecorder.setOutputFile(getFilePath());
//                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//                    mediaRecorder.prepare();
//                    mediaRecorder.start();
//
//                    Toast.makeText(ReviseSpeaking.this, "Recording is started", Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
