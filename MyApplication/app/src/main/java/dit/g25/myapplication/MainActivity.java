package dit.g25.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import dit.g25.myapplication.modele.Billet;
import dit.g25.myapplication.modele.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    private EditText prenom;
    private EditText nom;
    private TextView date;
    private TextView dateDebut;
    private EditText message;
    private Button envoye;
    private EditText titre;

    //variables globales de la date
    Calendar dateCouranteGlobal;
    LocalDate dateLimiteGlobal;


    private DatePickerDialog.OnDateSetListener dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prenom = (EditText) findViewById(R.id.inputPrenom);
        nom = (EditText) findViewById(R.id.inputNom);
        date = (TextView) findViewById(R.id.inputDate);
        dateDebut = (TextView) findViewById(R.id.tvDateCourante);
        message = (EditText) findViewById(R.id.inputMessage);
        envoye = (Button) findViewById(R.id.btnSubmit);
        titre = (EditText) findViewById(R.id.inputTitre);

        LocalDate dateUn = LocalDate.of(2020, Month.MARCH, 11);
        LocalDate dateDeux = LocalDate.of(2020, Month.MARCH, 16);

        Billet mockBillet1 = new Billet(9029,"TEST", dateUn , "Mock Billet 1",
                dateDeux, "Danny","Ticketeur" );

        Billet mockBillet2 = new Billet(0303,"TEST2", dateUn , "Mock Billet 2",
                dateDeux, "Danny","Ticketeur" );

        DatabaseHandler db = DatabaseHandler.getInstance(this);
        //db.supprimer(mockBillet1);
        //db.supprimer(mockBillet2);
        db.inserer(mockBillet1);
        db.inserer(mockBillet2);

        // **Code pour les dates**
        // Afficher la date courante
        Calendar calendar = Calendar.getInstance();
        dateCouranteGlobal = calendar;
        String currentDate = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());

        TextView tvDateC = findViewById(R.id.tvDateCourante);
        tvDateC.setText(currentDate);

        // Permet de choisir une date limite
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int année = cal.get(Calendar.YEAR);
                int mois = cal.get(Calendar.MONTH);
                int jour = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, dateListener,
                        année, mois, jour);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dateLimiteGlobal = LocalDate.of(année,mois,jour);
            }
        });

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int année, int mois, int jour) {

                // Avoir une la même affichage que le mois courant.
                String month = "";
                DateFormatSymbols dfs = new DateFormatSymbols();
                String months[] = dfs.getMonths();
                month = months[mois];
                // fin
                String retourDate = month + " "+jour+", "+année;
                date.setText(retourDate);
            }
        };
        // **Fin du code pour les dates**

        // code du bouton

        envoye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(titre.getText().toString(),dateDebut.getText().toString(),message.getText().toString(),date.getText().toString(),nom.getText().toString(),
                        prenom.getText().toString());
            }
        });
    }
    // Configure le bouton pour récupérer les informations du formulaire
    private void validate(String unTitre,String dateCourante, String contenue, String uneDateLimite, String unNom, String unPrenom){
        //Obliger les utilisateurs a remplire certain champs
        if (unTitre.isEmpty()){
            titre.setError("Champ obligatoire");
        }
        if (contenue.isEmpty()){
            message.setError("Champ obligatoire");
        }
        if (unNom.isEmpty()){
            nom.setError("Champ obligatoire");
        }
        if (unPrenom.isEmpty()){
            prenom.setError("Champ obligatoire");
        }
        if (uneDateLimite.matches("Choisis une date")){
            uneDateLimite=null;
        }
        else {
            // Convertir la date en LocalDate

            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            // il faut un api min de 26

            LocalDate localdate = dateCouranteGlobal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dateLimite = dateLimiteGlobal;
            //Entrez les valeur dans l'objet Billet.
            Billet unBillet = new Billet(1,unTitre, localdate, contenue, dateLimite, unNom,"Ticketeur");

            unBillet.setDateLimite(dateLimite);

            String auteur = prenom.getText().toString() + " " + nom.getText().toString();
            unBillet.setNomAuteur(auteur);

            // Envoyer le billet dans la base de donnée
            DatabaseHandler db = DatabaseHandler.getInstance(this);
            db.inserer(unBillet);
        }
    }
    public void voirBillets(View view){
        Intent intent = new Intent(MainActivity.this, VoirBillets.class);
        startActivity(intent);
    }
}