package POJO;

import android.net.Uri;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Usuario on 04/03/2018.
 */

public class AsyncGetTask extends AsyncTask<String,Void,ArrayList<Score>> {
    public AsyncResponse delegate = null;
    Score response = new Score();
    ArrayList<Score> scoreList;
    @Override
    protected ArrayList<Score> doInBackground(String... strings) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("wwtbamandroid.appspot.com");
        builder.appendPath("rest");
        builder.appendPath("highscores");
        String username = strings[0];
        try{
            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(username);
            writer.flush();
            writer.close();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            response = gson.fromJson(reader,Score.class);
            scoreList.add(response);
            reader.close();
            connection.disconnect();
        }catch(IOException e){e.printStackTrace();}
        return scoreList;
    }
    protected void onPostExecute(){
        delegate.processFinish(scoreList);
    }

}

