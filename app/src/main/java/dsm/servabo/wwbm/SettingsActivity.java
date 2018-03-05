package dsm.servabo.wwbm;


import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import POJO.AsyncPostTask;

/**
 * Created by servabo on 12/02/2018.
 */

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences prefs;
    String friend;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Spinner spinner = (Spinner) findViewById(R.id.spnNumber);
        String[] letra = {"0","1","2","3"};
        final EditText friendET = findViewById(R.id.ptFriendName);

        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, letra));
        prefs = getApplicationContext().getSharedPreferences("SharedPreferencesWWBM", MODE_PRIVATE);
        final EditText name = findViewById(R.id.ptEnterName);
        Button btnAddFriend = findViewById(R.id.btnAddFriend);
        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncPostTask(name.getText().toString(),friendET.getText().toString()).execute();

            }
        });
    }

    @Override
    public void onPause() {

        EditText name = findViewById(R.id.ptEnterName);
        Spinner ayudas = findViewById(R.id.spnNumber);
        SharedPreferences.Editor editor = prefs.edit();

        editor.clear();

        editor.putString("Nombre",name.getText().toString());
        editor.putInt("Ayudas", (Integer.parseInt(ayudas.getSelectedItem().toString())));

        editor.apply();
        super.onPause();

    }

    @Override
    protected void onResume () {

        EditText name = findViewById(R.id.ptEnterName);
        Spinner ayudas = findViewById(R.id.spnNumber);

        name.setText(prefs.getString("Nombre",getString(R.string.unknown)));
        ayudas.setSelection(prefs.getInt("Ayudas", 3));
        super.onResume ();

    }
    public boolean isNetworkConnected(int network){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info;
        switch (network){
            case 0:
                info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                break;
            case 1:
                info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                break;
            default:
                info = manager.getActiveNetworkInfo();
                break;
        }
        return ((info != null) && (info.isConnected()));
    }
}
