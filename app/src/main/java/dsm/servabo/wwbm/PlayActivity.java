package dsm.servabo.wwbm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import POJO.AsyncPutTask;
import POJO.Question;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.YELLOW;


public class PlayActivity extends AppCompatActivity {
    ArrayList<Question> questionList = new ArrayList<>();
    Integer premio = 0;
    Integer sigPremio = 0;
    Integer numPregunta = 0;
    SharedPreferences shared;
    Button a1;
    Button a2;
    Button a3;
    Button a4;
    TextView iconPrecent, iconCall, iconPublic, iconMoneda;
    Typeface font = null;
    ArrayList<Integer> premioList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        font= FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME_SOLID);

        iconMoneda = findViewById(R.id.iconMoneda);
        iconMoneda.setTypeface(font);

        shared = getApplicationContext().getSharedPreferences("SharedPreferencesWWBM", MODE_PRIVATE);
        int nComodines=shared.getInt("Ayudas", 3);
        iniciarComodines(nComodines);


        final Intent starterIntent = getIntent();
        if (numPregunta == 0) {
            fillQuestionList();
            llenarListaPremios();
        }
        if(numPregunta<questionList.size()) {
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
                        preguntaFallada();

                    }
                }
            });
            a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals("2")) {
                        preguntaAcertada();
                    } else {
                        preguntaFallada();
                    }
                }
            });
           a3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals("3")) {
                        preguntaAcertada();
                    } else {
                        preguntaFallada();
                    }
                }
            });
            a4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (questionList.get(numPregunta).getRight().equals("4")) {
                        preguntaAcertada();
                    } else {
                        preguntaFallada();
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

        answer1.setVisibility(View.VISIBLE);
        answer2.setVisibility(View.VISIBLE);
        answer3.setVisibility(View.VISIBLE);
        answer4.setVisibility(View.VISIBLE);

        answer1.setBackgroundColor(0);
        answer2.setBackgroundColor(0);
        answer3.setBackgroundColor(0);
        answer4.setBackgroundColor(0);
    }
    protected void setPremioYPreguntaTxt(){
        TextView txtPremio = findViewById(R.id.txtPremio);
        txtPremio.setText(premio.toString());
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
        editor.clear();
        editor.putInt("premio",premio);
        editor.putInt("sigPremio",sigPremio);
        editor.putInt("numPregunta",numPregunta);
        editor.apply();
    }
    protected void finDelJuego(){
        saveState();
        //new AsyncPutTask(shared.getString("nombre",null),premio).execute(); //TODO
        Intent intent = new Intent(getApplicationContext(), EndGameActivity.class);
        finish();
        startActivity(intent);
    }
    protected void preguntaAcertada(){
        premio = premioList.get(sigPremio);
        sigPremio++;
        numPregunta++;
        saveState();
        setPremioYPreguntaTxt();
        setPreguntaText();
        setButtonResText(a1,a2,a3,a4);
    }

    protected void iniciarComodines(int nComodines){

        iconCall = findViewById(R.id.iconCall);
        iconCall.setTypeface(font);
        iconPrecent = findViewById(R.id.iconCincuenta);
        iconPrecent.setTypeface(font);
        iconPublic = findViewById(R.id.iconPublic);
        iconPublic.setTypeface(font);

        iconPublic.setTextColor(GREEN);
        iconCall.setTextColor(YELLOW);
        iconPrecent.setTextColor(BLACK);

        iconPublic.setVisibility(View.INVISIBLE);
        iconCall.setVisibility(View.INVISIBLE);
        iconPrecent.setVisibility(View.INVISIBLE);

        if(nComodines==0) return;
        if(nComodines>0){
            iconPublic.setVisibility(View.VISIBLE);
            iconPublic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Question actualQuestion = questionList.get(numPregunta);
                    int publico=Integer.parseInt(actualQuestion.getAudience());
                    if(publico==1) a1.setBackgroundColor(GREEN);
                    if(publico==2) a2.setBackgroundColor(GREEN);
                    if(publico==3) a3.setBackgroundColor(GREEN);
                    if(publico==4) a4.setBackgroundColor(GREEN);

                    iconPublic.setVisibility(View.INVISIBLE);

                }
            });
        }
        if(nComodines>1){
            iconCall.setVisibility(View.VISIBLE);
            iconCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Question actualQuestion = questionList.get(numPregunta);
                    int llamada=Integer.parseInt(actualQuestion.getPhone());
                    if(llamada==1) a1.setBackgroundColor(YELLOW);
                    if(llamada==2) a2.setBackgroundColor(YELLOW);
                    if(llamada==3) a3.setBackgroundColor(YELLOW);
                    if(llamada==4) a4.setBackgroundColor(YELLOW);

                    iconCall.setVisibility(View.INVISIBLE);

                }
            });
        }
        if(nComodines>2){
            iconPrecent.setVisibility(View.VISIBLE);
            iconPrecent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Question actualQuestion = questionList.get(numPregunta);
                    int cincuenta1=Integer.parseInt(actualQuestion.getFifty1());
                    int cincuenta2=Integer.parseInt(actualQuestion.getFifty2());

                    if(cincuenta1==1 || cincuenta2 ==1) a1.setVisibility(View.INVISIBLE);
                    if(cincuenta1==2 || cincuenta2 ==2) a2.setVisibility(View.INVISIBLE);
                    if(cincuenta1==3 || cincuenta2 ==3) a3.setVisibility(View.INVISIBLE);
                    if(cincuenta1==4 || cincuenta2 ==4) a4.setVisibility(View.INVISIBLE);

                    iconPrecent.setVisibility(View.INVISIBLE);

                }
            });
        }



    }
    protected void llenarListaPremios(){
        premioList = new ArrayList<>();
        premioList.add(0);
        premioList.add(100);
        premioList.add(200);
        premioList.add(300);
        premioList.add(500);
        premioList.add(1000);
        premioList.add(2000);
        premioList.add(4000);
        premioList.add(8000);
        premioList.add(16000);
        premioList.add(32000);
        premioList.add(64000);
        premioList.add(125000);
        premioList.add(250000);
        premioList.add(500000);
        premioList.add(1000000);
    }
    protected void preguntaFallada(){
        if(sigPremio >= 4 && sigPremio<9){
            sigPremio = 4;
            premio = premioList.get(sigPremio);
            numPregunta++;
            saveState();
            setPremioYPreguntaTxt();
            setPreguntaText();
            setButtonResText(a1,a2,a3,a4);
        }else if(sigPremio>=9){
            sigPremio = 9;
            premio = premioList.get(sigPremio);
            numPregunta++;
            saveState();
            setPremioYPreguntaTxt();
            setPreguntaText();
            setButtonResText(a1,a2,a3,a4);
        }else{
            sigPremio = 0;
            premio = premioList.get(sigPremio);
            numPregunta++;
            saveState();
            setPremioYPreguntaTxt();
            setPreguntaText();
            setButtonResText(a1,a2,a3,a4);
        }
    }
    @Override
    public void onPause(){
        saveState();
        super.onPause();
    }
    @Override
    public void onResume(){
        premio = shared.getInt("premio",0);
        sigPremio = shared.getInt("sigPremio",0);
        numPregunta = shared.getInt("numPregunta", 0);
        setPremioYPreguntaTxt();
        setPreguntaText();
        a1 = findViewById(R.id.botRes1);
        a2 = findViewById(R.id.botRes2);
        a3 = findViewById(R.id.botRes3);
        a4 = findViewById(R.id.botRes4);
        setButtonResText(a1,a2, a3, a4);
        super.onResume();
    }
}
