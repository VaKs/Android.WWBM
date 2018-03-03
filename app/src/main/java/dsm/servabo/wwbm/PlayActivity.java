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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        final Intent starterIntent = getIntent();
        if(numPregunta<questionList.size()) {
            fillQuestionList();
            shared = getApplicationContext().getSharedPreferences("SharedPreferencesWWBM", MODE_PRIVATE);
            setPremioYPreguntaTxt();
            setPreguntaText();
            Button answer1 = findViewById(R.id.botRes1);
            Button answer2 = findViewById(R.id.botRes2);
            Button answer3 = findViewById(R.id.botRes3);
            Button answer4 = findViewById(R.id.botRes4);
            setButtonResText(answer1, answer2, answer3, answer4);
            answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals(1)) {
                        premio = sigPremio;
                        sigPremio = sigPremio * 2;
                        numPregunta++;
                        saveState();
                   /* finish();
                    startActivity(starterIntent);*/
                        //refresh();
                    } else {
                        finDelJuego();

                    }
                }
            });
            answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals(2)) {
                        premio = sigPremio;
                        sigPremio = sigPremio * 2;
                        numPregunta++;
                        saveState();
                   /* finish();
                    startActivity(starterIntent);*/
                        //refresh();
                    } else {
                       finDelJuego();
                    }
                }
            });
            answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals(3)) {
                        premio = sigPremio;
                        sigPremio = sigPremio * 2;
                        numPregunta++;
                        saveState();
                   /* finish();
                    startActivity(starterIntent);*/
                        //refresh();
                    } else {
                        finDelJuego();
                    }
                }
            });
            answer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals(4)) {
                        premio = sigPremio;
                        sigPremio = sigPremio * 2;
                        numPregunta++;
                        saveState();
                   /* finish();
                    startActivity(starterIntent);*/
                        //refresh();
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
        editor.putInt("premio",premio);
        editor.putInt("sigPremio",sigPremio);
        editor.putInt("numPregunta",numPregunta);
        editor.apply();
    }
    //ESTE METODO FALTA POR ASIGNARLE EL VALOR DE LA ACTIVITY FIN DEL JUEGO
    protected void finDelJuego(){
        saveState();
        Intent intent = new Intent(getApplicationContext(), null);
        startActivity(intent);
    }

}
