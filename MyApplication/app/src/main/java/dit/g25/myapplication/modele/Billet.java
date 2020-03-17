package dit.g25.myapplication.modele;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Billet {

    //Parametres de la classe billet
    protected int idBillet=0;
    protected String titre="";
    protected LocalDate dateSoumis;
    protected String contenu="";
    protected LocalDate dateLimite;
    protected String nomAuteur="";
    protected String nomProjet="";
    protected ArrayList<String> tags=null;

    //Constructeur avec tout les parametres
    public Billet(int unID,String unTitre, LocalDate dateDebut, String unContenu,LocalDate dateFin,
                  String unAuteur,String unProjet, ArrayList<String> desTags) {
        idBillet = unID;
        contenu = unContenu;
        titre = unTitre;
        dateSoumis = dateDebut;
        dateLimite = dateFin;
        nomProjet= unProjet;
        nomAuteur = unAuteur;
        tags = desTags;
    }

    //Constructeur qui cree un objet de type Billet mais sans tags
    public Billet(int idBillet, String titre, LocalDate dateSoumis, String contenu,
                  LocalDate dateLimite, String nomAuteur, String nomProjet) {
        this.idBillet = idBillet;
        this.titre = titre;
        this.dateSoumis = dateSoumis;
        this.contenu = contenu;
        this.dateLimite = dateLimite;
        this.nomAuteur = nomAuteur;
        this.nomProjet = nomProjet;
    }

    //Methodes de la classe
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDateSoumis() {
        return dateSoumis;
    }

    public void setDateSoumis(LocalDate dateSoumis) {
        this.dateSoumis = dateSoumis;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }

    public int getIdBillet() {
        return idBillet;
    }

    public void setIdBillet(int idBillet) {
        this.idBillet = idBillet;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }


    @Override
    public String toString(){
        return "Numero et titre du Billet: #"+idBillet+" "+titre+" \tDate soumis: "+dateSoumis +
                " \nContenu du billet: "+contenu+ " \nNom de l'auteur: "+nomAuteur+" Nom du projet: "+
                nomProjet+" \nDate limite pour completer la tache: "+ dateLimite+"\n";
    }

    public static void main(String[] args) {

        LocalDate dateUn = LocalDate.of(2020, Month.MARCH, 11);
        LocalDate dateDeux = LocalDate.of(2020, Month.MARCH, 16);

        Billet test = new Billet(9029,"TEST", dateUn , "Ceci est un billet de test!!!",
                dateDeux, "Danny Villeda","Ticketeur" );

        System.out.println(test.toString());



    }

}