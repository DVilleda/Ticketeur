package dit.g25.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.*;
import dit.g25.myapplication.modele.Billet;
import dit.g25.myapplication.modele.DatabaseHandler;

public class VoirBillets extends AppCompatActivity {
    private TextView listBillets;
    protected String vueBillets="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_billets);
        listBillets = (TextView) findViewById(R.id.listBillets);

        DatabaseHandler db = DatabaseHandler.getInstance(this);

        //Lis tous les billets dans la BD et les ajoute dans un String
        List<Billet> listeBillets =db.trouverTout();
        if(listeBillets.size()>0) {
            for (int i = 0; i < listeBillets.size(); i++) {
                vueBillets += listeBillets.get(i)+"\n";
            }
        }
        listBillets.setText(vueBillets);
    }
    @Override
    protected void onDestroy() {
        DatabaseHandler db = DatabaseHandler.getInstance(this);
        db.close();
        super.onDestroy();
    }
}
