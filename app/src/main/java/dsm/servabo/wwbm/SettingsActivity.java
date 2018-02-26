package dsm.servabo.wwbm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by servabo on 12/02/2018.
 */

public class SettingsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Spinner spinner = (Spinner) findViewById(R.id.spnNumber);
        String[] letra = {"0","1","2","3"};
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, letra));


    }

    @Override
    public void onPause() {

        EditText name = findViewById(R.id.ptEnterName);
        Spinner ayudas = findViewById(R.id.spnNumber);
        SharedPreferences prefs =  PreferenceManager. getDefaultSharedPreferences (this );
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("Nombre",name.toString());
        editor.putInt("Ayudas", ((int) ayudas.getSelectedItem()));

        editor.apply();
        super.onPause();
    }

    @Override
    protected void onResume () {

        EditText name = findViewById(R.id.ptEnterName);
        Spinner ayudas = findViewById(R.id.spnNumber);
        SharedPreferences prefs =  PreferenceManager. getDefaultSharedPreferences (this );

        name.setText(prefs.getString("Nombre",""));
        ayudas.setSelection(prefs.getInt("Ayudas", 0));

        super.onResume ();
    }


}
