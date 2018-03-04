package dsm.servabo.wwbm;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import POJO.Score;
import databases.ScoreDAO;
import databases.ScoreDatabase;

public class EndGameActivity extends AppCompatActivity {
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);



        shared = getApplicationContext().getSharedPreferences("SharedPreferencesWWBM", MODE_PRIVATE);
        TextView numRightAns = findViewById(R.id.numRightAns);
        Integer nPreg = shared.getInt("numPregunta",0);
        numRightAns.setText(nPreg.toString());
        TextView numMoneyWon = findViewById(R.id.numMoneyWon);
        Integer nRAns = shared.getInt("premio",0);
        numMoneyWon.setText(nRAns.toString());

        String autor = shared.getString("Nombre",getString(R.string.unknown));

        final Score score = new Score(autor,nRAns.toString());
        new Thread(new Runnable() {
            @Override
            public void run(){
                ScoreDAO scoreDAO = ScoreDatabase.getInstance(getApplicationContext()).scoreDAO();
                scoreDAO.addScore(score);

            }
        }).start();

    }
}