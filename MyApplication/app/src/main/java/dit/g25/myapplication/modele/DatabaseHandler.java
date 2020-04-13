package dit.g25.myapplication.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    //Variable qui servira de singleton pour synchroniser les donnees de la bd avec l'app
    private static DatabaseHandler singletonInstance;

    /**
     * Cette methode est en charge de verifier si un singleton ou une instance de la BD existe deja
     * si une instance est presente on ne creera pas une nouvelle instance de la BD
     * @param context la fenetre ou l'utilisateur se trouve
     * @return retourne l'instance de la BD est donc permet de lire et ecrire dans la BD
     */
    public static synchronized DatabaseHandler getInstance(Context context){
        if (singletonInstance == null) {
            singletonInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return singletonInstance;
    }

    //Nom de la Table
    public static final String nomTable = "Billets";

    //Nom des colonnes
    public static  final String billetID ="billetID";
    public static final String titre ="Titre";
    public static final String dateSoumis = "date_soumis";
    public static final String contenu = "Contenu";
    public static final String dateLimite = "Date_limite";
    public static final String nomProjet = "nom_projet";
    public static final String nomAuteur = "nom_auteur";

    //nom de la BD
    static final String dbname = "BDBilletAndroid.db";

    //Version de la BD
    static final int dbversion =1;

    //Query pour la creation des tables de la BD
    private static final String createTable = "CREATE TABLE Billets (\n" +
            "    billetID    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "    Titre       STRING  NOT NULL,\n" +
            "    date_soumis DATE    NOT NULL,\n" +
            "    Date_limite DATE    NOT NULL,\n" +
            "    Contenu     STRING  NOT NULL,\n" +
            "    nom_projet  STRING  NOT NULL,\n" +
            "    nom_auteur          NOT NULL\n" +
            ");";

    /**
     * Constructeur de la classe
     * @param context to use for locating paths to the the database
     */
    private DatabaseHandler(@Nullable Context context) {
        super(context, dbname, null, dbversion);
    }

    /**
     * Cette methode s'occupe de creer les tables de la BD
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    //Permet de mettre a jour la BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ nomTable);
        onCreate(db);
    }

    /**
     * Permet de lire un objet dans la bd
     * @param id le id du billet qu'on cherche
     * @return le Billet trouve dans la BD
     */
    public Billet lire(int id){
        SQLiteDatabase db = getReadableDatabase();

        //Tables de la BD utilise
        String [] projection = {
                billetID,
                titre,
                dateSoumis,
                dateLimite,
                contenu,
                nomProjet,
                nomAuteur
        };
        //Filtre de recherche
        String filtre = billetID + " = ?";

        Cursor cursor = db.query(nomTable,projection,filtre,new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();

        //Creer un objet de type Billet avec les infos lue dans la BD
        Billet billet = new Billet(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), LocalDate.parse(cursor.getString(2)),
                cursor.getString(4),LocalDate.parse(cursor.getString(3)),
                cursor.getString(6),cursor.getString(5));
        return billet;
    }

    /**
     * Fonction qui obtient tous les objets dans la base de donnees
     * @return retourne un liste qui contienne tous les billets dans la BD
     */
    public ArrayList<Billet> trouverTout(){
        ArrayList<Billet> listeBillets = new ArrayList<Billet>();
        //Creer un String qui contient le query
        String selectQuery = "SELECT * FROM " + nomTable;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //Boucle qui ajoute dans une liste tous les objets trouve dans la BD
        if(cursor.moveToFirst()){
            do{
                Billet billet = new Billet(Integer.parseInt(cursor.getString(0)),cursor.getString(1), LocalDate.parse(cursor.getString(2)),
                        cursor.getString(4),LocalDate.parse(cursor.getString(3)),
                        cursor.getString(6),cursor.getString(5));
                listeBillets.add(billet);
            }while(cursor.moveToNext());
        }
        return listeBillets;
    }

    /**
     * Permet d'inserer un Billet dans la BD
     * @param unBillet
     */
    public void inserer(Billet unBillet){
        SQLiteDatabase db = getWritableDatabase();

        /**
         * La variable values est charge de prendre les parametres du billet
         * qui seront utilises pour completer le Query a la BD
         */
        ContentValues values = new ContentValues();
        values.put(billetID,unBillet.getIdBillet());
        values.put(titre,unBillet.getTitre());
        values.put(dateSoumis,unBillet.getDateSoumis().toString());
        values.put(dateLimite,unBillet.getDateLimite().toString());
        values.put(contenu,unBillet.getContenu());
        values.put(nomProjet,unBillet.getNomProjet());
        values.put(nomAuteur,unBillet.getNomAuteur());
        db.insert(nomTable,null,values);
    }

    /**
     * Permet de modifier un Billet dans la BD
     * @param billet
     * @return le Billet tel qu'il a etait modifie
     */
    public int modifier(Billet billet){
        SQLiteDatabase db = this.getWritableDatabase();

        /**
         * La variable values est charge de prendre les parametres du billet
         * qui seront utilises pour completer le Query a la BD
         */
        ContentValues values = new ContentValues();
        values.put(contenu,billet.getContenu());
        values.put(dateLimite,billet.getDateLimite().toString());
        values.put(nomProjet,billet.getNomProjet());

        return db.update(nomTable,values,billetID + " = ?",
                new String[]{String.valueOf(billet.getIdBillet())});
    }

    /**
     * Cette methode permet de supprimer un billet dans la base de donnees
     * @param billet
     */
    public void supprimer(Billet billet){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(nomTable,billetID + " = ?",
                new String[]{String.valueOf(billet.getIdBillet())});
        db.close();
    }

}
