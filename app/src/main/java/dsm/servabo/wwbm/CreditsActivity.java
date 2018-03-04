package dsm.servabo.wwbm;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by servabo on 12/02/2018.
 */

public class CreditsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        Typeface fontBrand= FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME_BRAND);
        Typeface fontRegular= FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME_REGULAR);

        TextView iconCopy,iconProfe,iconStack,iconAndroidDevs,iconCopyrigth;
        iconCopy = findViewById(R.id.iconCopy);
        iconCopy.setTypeface(fontRegular);
        iconProfe = findViewById(R.id.iconProfe);
        iconProfe.setTypeface(fontRegular);
        iconStack = findViewById(R.id.iconStack);
        iconStack.setTypeface(fontBrand);
        iconAndroidDevs = findViewById(R.id.iconAndroid);
        iconAndroidDevs.setTypeface(fontBrand);

    }
}
