package dsm.servabo.wwbm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import POJO.Question;


public class PlayActivity extends AppCompatActivity {
    ArrayList<Question> questionList = new ArrayList<>();
    Integer premio = 0;
    Integer sigPremio = 1;
    Integer numPregunta = 0;
    SharedPreferences shared;
    Button a1;
    Button a2;
    Button a3;
    Button a4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        final Intent starterIntent = getIntent();
        if (numPregunta == 0) {
            fillQuestionList();
        }
        if(numPregunta<questionList.size()) {
            shared = getApplicationContext().getSharedPreferences("SharedPreferencesWWBM", MODE_PRIVATE);
            setPremioYPreguntaTxt();
            setPreguntaText();
            a1 = findViewById(R.id.botRes1);
            a2 = findViewById(R.id.botRes2);
            a3 = findViewById(R.id.botRes3);
            a4 = findViewById(R.id.botRes4);
            setButtonResText(a1,a2, a3, a4);
            a1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals("1")) {
                        preguntaAcertada();
                    } else {
                        finDelJuego();

                    }
                }
            });
            a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals("2")) {
                        preguntaAcertada();
                    } else {
                        finDelJuego();
                    }
                }
            });
           a3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals("3")) {
                        preguntaAcertada();
                    } else {
                        finDelJuego();
                    }
                }
            });
            a4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals("4")) {
                        preguntaAcertada();
                    } else {
                        finDelJuego();
                    }
                }
            });
        }else{
            finDelJuego();
        }

    }
    protected void setPreguntaText(){
        Question aux = questionList.get(numPregunta);
        TextView pregunta = findViewById(R.id.txtPregunta);
        pregunta.setText(aux.getText());
    }
    protected void setButtonResText(Button answer1, Button answer2, Button answer3, Button answer4){
        Question aux = questionList.get(numPregunta);
        answer1.setText(aux.getAnswer1());
        answer2.setText(aux.getAnswer2());
        answer3.setText(aux.getAnswer3());
        answer4.setText(aux.getAnswer4());
    }
    protected void setPremioYPreguntaTxt(){
        TextView txtPremio = findViewById(R.id.txtPremio);
        txtPremio.setText(sigPremio.toString());
        TextView numePregunta = findViewById(R.id.numPreg);
        numePregunta.setText(numPregunta.toString());
    }
    protected void fillQuestionList(){
        try{
            XmlPullParser parser = getResources().getXml(R.xml.questions);
            while(parser.getEventType() != XmlPullParser.END_DOCUMENT){
                int event = parser.next();
                Question question = new Question();
                if(event == XmlPullParser.START_TAG) {
                    question.setAnswer1(parser.getAttributeValue(null, "answer1"));
                    question.setAnswer2(parser.getAttributeValue(null, "answer2"));
                    question.setAnswer3(parser.getAttributeValue(null, "answer3"));
                    question.setAnswer4(parser.getAttributeValue(null, "answer4"));
                    question.setAudience(parser.getAttributeValue(null, "audience"));
                    question.setFifty1(parser.getAttributeValue(null, "fifty1"));
                    question.setFifty2(parser.getAttributeValue(null, "fifty2"));
                    question.setNumber(parser.getAttributeValue(null, "number"));
                    question.setPhone(parser.getAttributeValue(null, "phone"));
                    question.setRight(parser.getAttributeValue(null, "right"));
                    question.setText(parser.getAttributeValue(null, "text"));
                    questionList.add(question);
                }
            }
        }
        catch(XmlPullParserException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        questionList.remove(0);

    }
    protected void saveState(){
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("premio",premio.toString());
        editor.putString("sigPremio",sigPremio.toString());
        editor.putString("numPregunta",numPregunta.toString());
        editor.apply();
    }
    protected void finDelJuego(){
        saveState();
        Intent intent = new Intent(getApplicationContext(), EndGameActivity.class);
        finish();
        startActivity(intent);
    }
    protected void preguntaAcertada(){
        premio = sigPremio;
        sigPremio = sigPremio * 2;
        numPregunta++;
        saveState();
        setPremioYPreguntaTxt();
        setPreguntaText();
        setButtonResText(a1,a2,a3,a4);
    }
}
