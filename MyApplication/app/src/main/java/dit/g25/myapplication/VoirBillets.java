package dit.g25.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
<<<<<<< HEAD
=======

import java.time.LocalDate;
import java.time.Month;
>>>>>>> 5f25e6d746ff95b69900009f659152157ad7ed95
import java.util.*;
import dit.g25.myapplication.modele.Billet;
import dit.g25.myapplication.modele.DatabaseHandler;

public class VoirBillets extends AppCompatActivity {
    private TextView listBillets;
<<<<<<< HEAD
    protected String vueBillets="";
=======
    protected String vueBillets;
>>>>>>> 5f25e6d746ff95b69900009f659152157ad7ed95

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_billets);
        listBillets = (TextView) findViewById(R.id.listBillets);

        DatabaseHandler db = DatabaseHandler.getInstance(this);

<<<<<<< HEAD
        //Obtenir tous les billets dans la BD et les mettre dans un string
=======
>>>>>>> 5f25e6d746ff95b69900009f659152157ad7ed95
        List<Billet> listeBillets =db.trouverTout();
        if(listeBillets.size()>0) {
            for (int i = 0; i < listeBillets.size(); i++) {
                vueBillets += listeBillets.get(i)+"\n";
            }
        }
<<<<<<< HEAD
        //Changer le textview avec le contenu du string qui contient tous les billets
=======
>>>>>>> 5f25e6d746ff95b69900009f659152157ad7ed95
        listBillets.setText(vueBillets);
    }
}
