package dsm.servabo.wwbm;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import POJO.Score;
import databases.ScoreDAO;
import databases.ScoreDatabase;

public class EndGameActivity extends AppCompatActivity {
    SharedPreferences shared;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        builder = new AlertDialog.Builder(this);

        shared = getApplicationContext().getSharedPreferences("SharedPreferencesWWBM", MODE_PRIVATE);
        TextView numRightAns = findViewById(R.id.numRightAns);
        Integer nPreg = shared.getInt("pregAcertada",0);
        numRightAns.setText(nPreg.toString());
        TextView numMoneyWon = findViewById(R.id.numMoneyWon);
        Integer nRAns = shared.getInt("premio",0);
        numMoneyWon.setText(nRAns.toString());

        String autor = shared.getString("Nombre",getString(R.string.unknown));
        Integer nComodines = shared.getInt("Ayudas",3);
        final Score score = new Score(autor,nRAns.toString());
        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    ScoreDAO scoreDAO = ScoreDatabase.getInstance(getApplicationContext()).scoreDAO();
                    scoreDAO.addScore(score);
                } catch(Exception e){
                    builder.setMessage(getString(R.string.DBProblem));
                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}});
                    builder.create().show();
            }

            }
        }).start();
        SharedPreferences.Editor editor;
        editor = shared.edit();
        editor.clear();
        editor.putString("Nombre", autor);
        editor.putInt("Ayudas", nComodines);
        editor.apply();
    }
}