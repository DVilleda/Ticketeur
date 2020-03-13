package modele;

import java.time.LocalDate;
import java.time.Month;

public class Billet {

    //Parametres de la classe billet
    protected int idBillet=0;
    protected String titre="";
    protected LocalDate dateSoumis;
    protected String contenu="";
    protected LocalDate dateLimite;
    protected String nomAuteur="";

    //Constructeur
    public Billet(String unTitre, LocalDate dateDebut, String unContenu) {
        contenu = unContenu;
        titre = unTitre;
        dateSoumis = dateDebut;
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

    @Override
    public String toString() {
        return "Billet {" +
                "titre = '" + titre + '\'' +
                ", dateSoumis = " + dateSoumis.toString() +
                ", contenu='" + contenu + '\'' +
                /**", dateLimite = " + dateLimite.toString() +*/
                '}';
    }

    public static void main(String[] args) {

        LocalDate dateUn = LocalDate.of(2020, Month.MARCH, 11);
        LocalDate dateDeux = LocalDate.of(2020, Month.MARCH, 16);

        Billet test = new Billet("TEST", dateUn , "Ceci est un billet de test!!!" );

        System.out.println(test.toString());



    }

}