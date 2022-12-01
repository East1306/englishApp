package com.nhi.english;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import android.app.Activity;
class QuestionNare {
    public String ID,title,text;
}
public class Activity_test extends Activity {
    TextView Cauhoi;
    RadioGroup RG;
    Button BT;
    RadioButton A,B,C,D;
    int pos=0;
    int kq=0;
    ArrayList<QuestionNare> L = new ArrayList();
    int HighScore = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gramma_vocab);
        Cauhoi = (TextView) findViewById(R.id.TxtCauhoi);
        RG = (RadioGroup)findViewById(R.id.radioGroup);
        BT = (Button) findViewById(R.id.BtnTraloi);
        A = (RadioButton)findViewById(R.id.rb1);
        B = (RadioButton)findViewById(R.id.rb2);
        C = (RadioButton)findViewById(R.id.rb3);
        D = (RadioButton)findViewById(R.id.rb4);

    }
}
