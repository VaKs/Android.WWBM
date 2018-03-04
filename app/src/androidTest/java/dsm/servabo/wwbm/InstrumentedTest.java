package dsm.servabo.wwbm;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import POJO.Score;
import databases.ScoreDAO;
import databases.ScoreDatabase;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    static Context appContext = InstrumentationRegistry.getTargetContext();
    static ScoreDAO scoreDAO = ScoreDatabase.getInstance(appContext).scoreDAO();
    List<Score> scoreList= new ArrayList<>();
    Score score;
    Score score1;
    Score score2;
    Score score3;

    @Before
    public void declaraciones(){

        score = new Score("Sergio","300");
        score1 = new Score("David","400");
        score2 = new Score("Sergio","1500");
        score3 = new Score("David","1400");

        scoreList= new ArrayList<>();
        scoreList.add(score);
        scoreList.add(score1);
        scoreList.add(score2);
        scoreList.add(score3);
    }
    @AfterClass
    public static void LimpiarDb(){
        scoreDAO.clearScores();
    }
    @Test
    public void dbAddScore() throws Exception {
         try {
            scoreDAO.addScore(score);
            scoreDAO.addScore(score1);
            scoreDAO.addScore(score2);
            scoreDAO.addScore(score3);

            List<Score> scoreList = scoreDAO.getScores();

            assertEquals(score.getName(),scoreList.get(0).getName());
            assertEquals(score.getScoring(),scoreList.get(0).getScoring());
            assertEquals(score1.getName(),scoreList.get(1).getName());
            assertEquals(score1.getScoring(),scoreList.get(1).getScoring());
            assertEquals(score2.getName(),scoreList.get(2).getName());
            assertEquals(score2.getScoring(),scoreList.get(2).getScoring());
            assertEquals(score3.getName(),scoreList.get(3).getName());
            assertEquals(score3.getScoring(),scoreList.get(3).getScoring());

        } catch (Exception e){
            System.out.print(e.toString());
        }

    }
    @Test
    public void dbGetScores() throws Exception {
        try {
            List<Score> scoreListDb = scoreDAO.getScores();
            for(int i=0;i<=scoreListDb.size();i++){
                assertEquals(scoreListDb.get(i).getName(),scoreList.get(i).getName());
                assertEquals(scoreListDb.get(i).getScoring(),scoreList.get(i).getScoring());
            }

        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
    @Test
    public void dbClearScores() throws Exception {
        try {
            scoreDAO.clearScores();
            List<Score> scoreListDb = scoreDAO.getScores();
            assertEquals(scoreListDb.isEmpty(),true);


        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}
