package dsm.servabo.wwbm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import POJO.Question;

/**
 * Created by servabo on 12/02/2018.
 */


public class PlayActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        try {
            File xmlFile = (File) getResources().getXml(R.xml.questions);
            ArrayList<Question> questionList = new ArrayList<>();
            Question question = new Question();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("question");
            for(int temp =0; temp<nList.getLength();temp++){
                Node nNode =nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    question.setAnswer1(eElement.getAttribute("answer1"));
                    question.setAnswer2(eElement.getAttribute("answer2"));
                    question.setAnswer3(eElement.getAttribute("answer3"));
                    question.setAnswer4(eElement.getAttribute("answer4"));
                    question.setAudience(eElement.getAttribute("audience"));
                    question.setFifty1(eElement.getAttribute("fifty1"));
                    question.setFifty2(eElement.getAttribute("fifty2"));
                    question.setNumber(eElement.getAttribute("number"));
                    question.setPhone(eElement.getAttribute("phone"));
                    question.setRight(eElement.getAttribute("right"));
                    question.setText(eElement.getAttribute("text"));
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
