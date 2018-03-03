package dsm.servabo.wwbm;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
    }
}
