package dsm.servabo.wwbm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by servabo on 12/02/2018.
 */

public class ScoresActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        ArrayList<String> listItems=new ArrayList<String>();
        ArrayAdapter<String> adapter;
        ListView list = (ListView) findViewById(R.id.listLocal);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);
        listItems.add("Item añadido");

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();
        TabHost.TabSpec spec = host.newTabSpec("tab1");

        spec.setIndicator("Local",null);

        spec.setContent(R.id.listLocal);
        host.addTab(spec);

        spec = host.newTabSpec("tab2");

        spec.setIndicator("Friends",null);
        spec.setContent(R.id.listFriends);
        host.addTab(spec);




    }

}
