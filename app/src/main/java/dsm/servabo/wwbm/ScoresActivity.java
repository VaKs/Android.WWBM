package dsm.servabo.wwbm;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import tasks.AsyncGetTask;
import tasks.AsyncGetTaskFireBaseImp;
import tasks.AsyncResponse;
import POJO.Score;
import POJO.ScoreList;
import databases.ScoreDAO;
import databases.ScoreDatabase;

/**
 * Created by servabo on 12/02/2018.
 */

public class ScoresActivity extends AppCompatActivity implements AsyncResponse {
    Handler handler=null;
    SharedPreferences prefs;
    Score res = new Score();
    ScoreList scoreList = new ScoreList();
    final ArrayList<String> listItemsLocal=new ArrayList<>();
    ArrayList<String> listItemsFriends=new ArrayList<>();
    ArrayAdapter<String> adapterLocal, adapterFriends;
    AlertDialog.Builder builder;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        builder = new AlertDialog.Builder(this);

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
                try{
                    ScoreDAO scoreDAO = ScoreDatabase.getInstance(getApplicationContext()).scoreDAO();
                    for (Score score : scoreDAO.getScores()) {
                        listItemsLocal.add(score.getName()+"      "+score.getScoring());
                    }
                } catch(Exception e){
                    builder.setMessage(getString(R.string.DBProblem));
                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}});
                    builder.create().show();
                }

            }
        }).start();

        // items para la pestaña Friends
        String username = prefs.getString("Nombre","unknown");
        //new AsyncGetTask(this).execute(username);
        //new AsyncGetTaskFireBaseImp(this).execute("Algo");
        //FIREBASE
        Firebase firebase;
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://proyectoprueba-17d4b.firebaseio.com/scores");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren()) {
                    Score score = scoreSnapshot.getValue(Score.class);
                    listItemsFriends.add(score.getName() + "   " + score.getScoring());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


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
    public void processFinish(ScoreList response) {
        this.scoreList=response;
        if(scoreList.getScores()!=null) {
            for (int i = 0; i < scoreList.getScores().size(); i++) {
                listItemsFriends.add(scoreList.getScores().get(i).getName() + "   " + scoreList.getScores().get(i).getScoring());
            }
        } else{
            builder.setMessage(getString(R.string.NoConexion));
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}});
            builder.create().show();
        }
    }
}
