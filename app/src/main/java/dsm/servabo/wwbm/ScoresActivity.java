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

        ArrayList<String> listItemsLocal=new ArrayList<String>();
        ArrayList<String> listItemsFriends=new ArrayList<String>();
        ArrayAdapter<String> adapterLocal, adapterFriends;
        ListView listLocal = (ListView) findViewById(R.id.listLocal);
        ListView listFriends = (ListView) findViewById(R.id.listFriends);

        adapterLocal = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listItemsLocal);
        listLocal.setAdapter(adapterLocal);

        adapterFriends = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listItemsFriends);
        listFriends.setAdapter(adapterFriends);


        // items para la pestaña local
        listItemsLocal.add("Item 1.1");
        listItemsLocal.add("Item 1.2");
        listItemsLocal.add("Item 1.3");

        // items para la pestaña Friends
        listItemsFriends.add("Item 2.1");
        listItemsFriends.add("Item 2.2");
        listItemsFriends.add("Item 2.3");

        // inicializar y pintar pestañas
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
