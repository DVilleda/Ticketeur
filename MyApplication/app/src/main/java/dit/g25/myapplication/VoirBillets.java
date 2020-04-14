package dit.g25.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        List<Billet> listeBillets = db.trouverTout();
        if (listeBillets.size() > 0) {
            for (int i = 0; i < listeBillets.size(); i++) {
                vueBillets += "Numero du billet : "+listeBillets.get(i).getIdBillet()+"\n";
                vueBillets += "Auteur de billet : " +listeBillets.get(i).getNomAuteur()+"\n";
                vueBillets += "Titre du projet : " +listeBillets.get(i).getNomProjet()+"\n";
                vueBillets += "Contenu : "+listeBillets.get(i).getContenu()+"\n";
                vueBillets += "Date de création : "+listeBillets.get(i).getDateSoumis() + " Date Limite : "+listeBillets.get(i).getDateLimite()+"\n";
                vueBillets += "================================\n";
            }
        }
           // for (int i = 0; i < listeBillets.size(); i++) {
           //     vueBillets += listeBillets.get(i)+"\n";
         //   }
       // }
        listBillets.setText(vueBillets);
    }
    @Override
    protected void onDestroy() {
        DatabaseHandler db = DatabaseHandler.getInstance(this);
        db.close();
        super.onDestroy();
    }
    public void créerBillet(View view){
        Intent intent = new Intent(VoirBillets.this, MainActivity.class);
        startActivity(intent);
    }
}
