package dit.g25.myapplication.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler singletonInstance;

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
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     */
    private DatabaseHandler(@Nullable Context context) {
        super(context, dbname, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ nomTable);
        onCreate(db);
    }

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

        Billet billet = new Billet(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), LocalDate.parse(cursor.getString(2)),
                cursor.getString(4),LocalDate.parse(cursor.getString(3)),
                cursor.getString(5),cursor.getString(6));

        return billet;
    }

    public ArrayList<Billet> trouverTout(){
        ArrayList<Billet> listeBillets = new ArrayList<Billet>();

        String selectQuery = "SELECT * FROM " + nomTable;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

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
    public void inserer(Billet unBillet){
        SQLiteDatabase db = getWritableDatabase();

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

    public int modifier(Billet billet){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(contenu,billet.getContenu());
        values.put(dateLimite,billet.getDateLimite().toString());
        values.put(nomProjet,billet.getNomProjet());

        return db.update(nomTable,values,billetID + " = ?",
                new String[]{String.valueOf(billet.getIdBillet())});
    }

    public void supprimer(Billet billet){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(nomTable,billetID + " = ?",
                new String[]{String.valueOf(billet.getIdBillet())});
        db.close();
    }

}
