package tasks;

import android.net.Uri;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import POJO.Score;

/**
 * Created by Usuario on 03/03/2018.
 */

public class AsyncPutTask extends AsyncTask<Integer, Void, Void> {
    Score score;
    public AsyncPutTask(String username, Integer score){
        this.score=new Score(username,score.toString());

    }
    @Override
    protected Void doInBackground(Integer... integers) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("wwtbamandroid.appspot.com");
        builder.appendPath("rest");
        builder.appendPath("highscores");
        String req = "name="+score.getName()+"&score="+score.getScoring();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        try{
            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(req);
            writer.flush();
            writer.close();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            reader.read();
            connection.disconnect();
        }catch(IOException e){e.printStackTrace();}
        return null;
    }
}
