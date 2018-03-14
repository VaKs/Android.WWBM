package tasks;

import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import POJO.Score;
import POJO.ScoreList;
import dsm.servabo.wwbm.ScoresActivity;

/**
 * Created by Usuario on 04/03/2018.
 */

public class AsyncGetTaskFireBaseImp extends AsyncTask<String, Void, ScoreList> {
    public AsyncResponse delegate;
    private WeakReference<ScoresActivity> activity;
    ScoreList scoreList1= new ScoreList();
    Firebase firebase;
    List<Score> list = new ArrayList<>();

    private DatabaseReference mDatabase;


    public AsyncGetTaskFireBaseImp(ScoresActivity activity) {
        this.activity = new WeakReference<>(activity);
    }
    @Override
    protected ScoreList doInBackground(String... strings) {
        Firebase.setAndroidContext(activity.get().getApplicationContext());
        firebase = new Firebase("https://proyectoprueba-17d4b.firebaseio.com/scores");

        //mDatabase = FirebaseDatabase.getInstance().getReference();

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot score : dataSnapshot.getChildren()) {
                    Score aux = score.getValue(Score.class);
                    list.add(aux);
                }
                scoreList1.setScores(list);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return scoreList1;
/*
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("proyectoprueba-17d4b.firebaseio.com");
        builder.appendPath("scores.json");
        try{
            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            if(connection.getResponseCode()>=200 && connection.getResponseCode()<300 ) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                scoreList1 = gson.fromJson(reader, ScoreList.class);
                reader.close();
            }
            connection.disconnect();

        }catch(Exception e){e.printStackTrace();}
        return scoreList1;
        */
    }

    @Override
    protected void onPostExecute(ScoreList scoreList) {
        activity.get().processFinish(scoreList);
    }

}

