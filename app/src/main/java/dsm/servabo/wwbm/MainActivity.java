package dsm.servabo.wwbm;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView iconPlay,iconSettings,iconAbout,iconScores;
        Typeface font= FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME_SOLID);

        iconPlay = findViewById(R.id.iconPlay);
        iconPlay.setTypeface(font);
        iconSettings = findViewById(R.id.iconSettings);
        iconSettings.setTypeface(font);
        iconScores = findViewById(R.id.iconScores);
        iconScores.setTypeface(font);
        iconAbout = findViewById(R.id.iconCredits);
        iconAbout.setTypeface(font);


        Button play = findViewById(R.id.btnPlay);
        play.setBackgroundColor(0);
        iconPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PlayActivity.class);
                startActivity(intent);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PlayActivity.class);
                startActivity(intent);
            }
        });

        Button settings = findViewById(R.id.btnSettings);
        settings.setBackgroundColor(0);
        iconSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
            }
        });

        Button scores = findViewById(R.id.btnScores);
        scores.setBackgroundColor(0);
        iconScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScoresActivity.class);
                startActivity(intent);
            }
        });
        scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScoresActivity.class);
                startActivity(intent);
            }
        });

        Button credits = findViewById(R.id.btnCredits);
        credits.setBackgroundColor(0);
        iconAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreditsActivity.class);
                startActivity(intent);
            }
        });
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreditsActivity.class);
                startActivity(intent);
            }
        });
    }
}
