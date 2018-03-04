package dsm.servabo.wwbm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

import POJO.AsyncGetTask;
import POJO.AsyncResponse;
import POJO.Score;
import databases.ScoreDAO;
import databases.ScoreDatabase;

/**
 * Created by servabo on 12/02/2018.
 */

public class ScoresActivity extends AppCompatActivity implements AsyncResponse {
    Handler handler=null;
    SharedPreferences prefs;
    Score res = new Score();
    ArrayList<Score> scoreList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);


        final ArrayList<String> listItemsLocal=new ArrayList<>();
        ArrayList<String> listItemsFriends=new ArrayList<>();
        ArrayAdapter<String> adapterLocal, adapterFriends;
        ListView listLocal = findViewById(R.id.listLocal);
        ListView listFriends =findViewById(R.id.listFriends);
        prefs = getApplicationContext().getSharedPreferences("SharedPreferencesWWBM", MODE_PRIVATE);

        adapterLocal = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listItemsLocal);
        listLocal.setAdapter(adapterLocal);

        adapterFriends = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listItemsFriends);
        listFriends.setAdapter(adapterFriends);


        // items para la pestaña local
        new Thread(new Runnable() {
            @Override
            public void run(){
                ScoreDAO scoreDAO = ScoreDatabase.getInstance(getApplicationContext()).scoreDAO();
                for (Score score : scoreDAO.getScores()) {
                    listItemsLocal.add(score.getName()+"      "+score.getScoring());
                }

            }
        }).start();

/*
        // items para la pestaña Friends  //TODO
        String username = prefs.getString("name",null);
        new AsyncGetTask().execute(username);
        for(int i = 0; i<scoreList.size();i++){
            listItemsFriends.add(scoreList.get(i).getName()+"   "+scoreList.get(i).getScoring());
        }
*/
        // inicializar y pintar pestañas
        TabHost host = findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator(getString(R.string.local),null);
        spec.setContent(R.id.listLocal);
        host.addTab(spec);

        spec = host.newTabSpec("tab2");
        spec.setIndicator(getString(R.string.Friends),null);
        spec.setContent(R.id.listFriends);
        host.addTab(spec);




    }

    @Override
    public void processFinish(ArrayList<Score> response) {
        this.scoreList = response;
    }
}
