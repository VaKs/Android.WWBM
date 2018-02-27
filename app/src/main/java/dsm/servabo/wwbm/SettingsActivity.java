package dsm.servabo.wwbm;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by servabo on 12/02/2018.
 */

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences prefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Spinner spinner = (Spinner) findViewById(R.id.spnNumber);
        String[] letra = {"0","1","2","3"};
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, letra));
        prefs = getApplicationContext().getSharedPreferences("SharedPreferencesWWBM", MODE_PRIVATE);
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

        name.setText(prefs.getString("Nombre","aaa"));
        ayudas.setSelection(prefs.getInt("Ayudas", 1));
        super.onResume ();

    }


}
