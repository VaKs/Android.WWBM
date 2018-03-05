package POJO;

import android.net.Uri;
import android.os.AsyncTask;
import android.net.Uri.Builder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import dsm.servabo.wwbm.SettingsActivity;

/**
 * Created by Usuario on 03/03/2018.
 */

public class AsyncPostTask extends AsyncTask<String, Void, Void> {
    String username;
    String friend;
    public AsyncPostTask(String username, String friend){
        this.username = username;
        this.friend = friend;
    }
    @Override
    protected Void doInBackground(String... strings) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("wwtbamandroid.appspot.com");
        builder.appendPath("rest");
        builder.appendPath("friends");
        String req = "name="+username+"&"+"friend_name="+friend;
        try{
            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
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
