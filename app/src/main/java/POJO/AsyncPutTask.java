package POJO;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Usuario on 03/03/2018.
 */

public class AsyncPutTask extends AsyncTask<Integer, Void, Void> {
    String username;
    Integer score;
    public AsyncPutTask(String username, Integer score){
        this.username = username;
        this.score = score;
    }
    @Override
    protected Void doInBackground(Integer... integers) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("wwtbamandroid.appspot.com");
        builder.appendPath("rest");
        builder.appendPath("friends");
        Integer score = integers[0];
        try{
            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(score);
            writer.flush();
            writer.close();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            reader.read();
            connection.disconnect();
        }catch(IOException e){e.printStackTrace();}
        return null;
    }
}
