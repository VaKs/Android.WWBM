package POJO;

import android.net.Uri;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import dsm.servabo.wwbm.ScoresActivity;

/**
 * Created by Usuario on 04/03/2018.
 */

public class AsyncGetTask extends AsyncTask<String, Void, ScoreList> {
    public AsyncResponse delegate;
    private WeakReference<ScoresActivity> activity;
    ScoreList scoreList1= new ScoreList();


    public AsyncGetTask(ScoresActivity activity) {
        this.activity = new WeakReference<>(activity);
    }
    @Override
    protected ScoreList doInBackground(String... strings) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("wwtbamandroid.appspot.com");
        builder.appendPath("rest");
        builder.appendPath("highscores");
        builder.appendQueryParameter("name",strings[0]);
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
    }

    @Override
    protected void onPostExecute(ScoreList scoreList) {
        activity.get().processFinish(scoreList);
    }

}

