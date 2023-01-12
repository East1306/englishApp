package com.nhi.english.Revise_PhuongDong.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;

import com.nhi.english.R;
import com.nhi.english.Revise_PhuongDong.Answer;
import com.nhi.english.Revise_PhuongDong.Question;
import com.nhi.english.Revise_PhuongDong.Result;
import com.nhi.english.Revise_PhuongDong.Revise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class RecordFragment extends AppCompatActivity {
    private NavController navController;

    private ImageButton recordBtn;
    private TextView filenameText;
    private Button submit;
    private  ImageButton back;
    private TextView content;

    //Giữ những câu hỏi _ câu trả lời từ class Revise qua
    ArrayList<Question> listQuestion = new ArrayList<>();
    ArrayList<Answer> listAnswer = new ArrayList<>();
    String sound, result;
    private ArrayList<String> list_question = new ArrayList<>();
    int valueRandom;

//    final AlertDialog.Builder adb = new AlertDialog.Builder(this);

    private  boolean isRecording = false;

    private String recordPermission = Manifest.permission.RECORD_AUDIO;

    private int PERMISSION_CODE = 21;

    private MediaRecorder mediaRecorder;
    private String recordFile;

    private Chronometer timer;

    public RecordFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaking);

        recordBtn =findViewById(R.id.btnRecord);
        timer = findViewById(R.id.chronometer);
        filenameText = findViewById(R.id.recording_status_txt);
        submit = (Button) findViewById(R.id.buttonSubmit);
        back = (ImageButton) findViewById(R.id.ic_back);
        content = findViewById(R.id.contentSpeaking);

        Intent callerIntent = getIntent();
        //        //Lấy Bundle dựa vào Revise
        Bundle packageFormCaller= callerIntent.getBundleExtra("Revise");
        listQuestion = packageFormCaller.getParcelableArrayList("Question");
        listAnswer = packageFormCaller.getParcelableArrayList("Answer");
        sound = packageFormCaller.getString("Sound");
        result = packageFormCaller.getString("Result");
        valueRandom = packageFormCaller.getInt("IdQuestion speak");

        ReadData();
        if(valueRandom == 0){ //Từ revise chyển qua
            valueRandom = (int)(Math.random() * list_question.size());
        }

        Display(valueRandom);

        for (Question i: listQuestion){
            Log.d("Question", i.content);
        }

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRecording){
                    //Stop Recording
                    stopRecording();

                    recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_record_24, null));
                    isRecording = false;
                }else{
                    if(checkPermission()){
                        startRecording();

                        recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_record, null));
                        isRecording = true;
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordFragment.this,
                        Revise.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("Question", listQuestion);
                bundle.putParcelableArrayList("Answer", listAnswer);
                bundle.putString("Sound", sound);
                bundle.putString("Result", result);
                bundle.putInt("IdQuestion speak", valueRandom);
                intent.putExtra("Speaking", bundle);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                adb.setTitle("Confirm?");
//                adb.setMessage("Plese Confirm");
//                adb.setNegativeButton("Cancel", null);
//                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int arg1) {
//                        // TODO Auto-generated method stub
//                        Toast.makeText(RecordFragment.this,"Your Click OK. " ,
//                                Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(RecordFragment.this, Result.class);
//                        intent.putExtra("Result", result);
//                        intent.putExtra("Total sentense", String.valueOf(listQuestion.size()));
//                        startActivity(intent);
//                    }
//                });
//                adb.show();
                Intent intent = new Intent(RecordFragment.this, Result.class);
                intent.putExtra("Result", result);
                intent.putExtra("Total sentense", String.valueOf(listQuestion.size()));
                startActivity(intent);
            }
        });
    }

    private void startRecording() {
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();

        String recordPath = this.getExternalFilesDir("/").getAbsolutePath();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy", Locale.ENGLISH);
        Date now = new Date();

        recordFile = "Speaking_" + formatter.format(now) + ".3gp";

        filenameText.setText("Recording, file name: " + recordFile);
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(recordPath + "/" + recordFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
    }


    private void stopRecording() {
        timer.stop();

        filenameText.setText("Stopped, file saved: " + recordFile);

        try {
            mediaRecorder.stop();
        } catch(RuntimeException stopException) {
            Log.d("Record", "FAILED......");
        }
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private boolean checkPermission() {
        if(ActivityCompat.checkSelfPermission(this, recordPermission) == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            ActivityCompat.requestPermissions(this, new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }
    }
    private void ReadData() {
        try{
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();

            InputStream in = getAssets().open("data_reviseSpeak.xml");

            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();

            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element) {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("Content");
                    String content = listChild.item(0).getTextContent();
                    list_question.add(content);
                }
            }
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private void Display(int i){
        content.setText(list_question.get(i));
    }

}
