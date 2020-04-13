package modele;

import java.io.File;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.*;

public class BilletDAOSQLite extends BilletDAO{

    @Override
    public ArrayList<Billet> trouverTout() throws DAOException {
        ArrayList<Billet> billets=new ArrayList<Billet>();

        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:DBBillet.db");
            Statement stmt=conn.createStatement();

            if(stmt.execute("SELECT * FROM Billet")){
                ResultSet rs=stmt.getResultSet();

                while(rs.next()){
                    billets.add(new Billet(rs.getInt("billetID"), rs.getString("Titre"),
                            LocalDate.parse(rs.getString("date_soumis")), rs.getString("Contenu"),
                            LocalDate.parse(rs.getString("Date_limite")),rs.getString("nom_auteur"),
                            rs.getString("nom_projet")));
                }

                rs.close();
            }
            conn.close();
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        return billets;
    }

    /**
     * Lit un objet à partir de son id
     *
     * @param id l'identifiant unique de l'objet à lire
     * @return l'objet lu ou null si non trouvé
     */
    @Override
    public Billet lire(Object id) throws DAOException {
        Billet lue=null;
        Connection conn;

        try{
            conn = DriverManager.getConnection("jdbc:sqlite:DBBillet.db");

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Billet WHERE billetID=?");
            stmt.setInt(1,(int)id);
            ResultSet rs = stmt.executeQuery();

            lue=new Billet(rs.getInt("billetID"), rs.getString("Titre"),
                    LocalDate.parse(rs.getString("date_soumis")), rs.getString("Contenu"),
                    LocalDate.parse(rs.getString("Date_limite")),rs.getString("nom_auteur"),
                    rs.getString("nom_projet"));

            rs.close();
            conn.close();
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        return lue;
    }

    /**
     * Crée un nouvel objet
     *
     * @param objet l'objet à ajouter dans la source de données
     * @return l'objet tel qu'il a été créé dans la source de données
     */
    @Override
    public Billet creer(Billet objet) throws DAOException {
        Connection conn = null;
        Billet ecrit = objet;


        try{
            conn = DriverManager.getConnection("jdbc:sqlite:DBBillet.db");

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Billet" +
                    "(billetID,date_soumis,Titre,Contenu,Date_limite,nom_projet,nom_auteur) VALUES(?,?,?,?,?,?,?)");

            stmt.setInt(1,ecrit.getIdBillet());
            stmt.setString(2, ecrit.getDateSoumis().toString());
            stmt.setString(3,ecrit.getTitre());
            stmt.setString(4,ecrit.getContenu());
            stmt.setString(5,ecrit.getDateLimite().toString());
            stmt.setString(6,ecrit.getNomProjet());
            stmt.setString(7,ecrit.getNomAuteur());

            stmt.executeUpdate();
            stmt.close();

        }catch (SQLException e){
          throw new DAOException(e);
        }
        return lire(ecrit.getIdBillet());
    }

    /**
     * Modifie un objet dans la source de données
     *
     * @param objet l'objet à modifier dans la source de données
     * @return l'objet tel qu'il a été modifié dans la source de données
     */
    @Override
    public Billet modifier(Billet objet) throws DAOException {
        Connection conn;
        Billet ecrit = objet;


        try{
            conn = DriverManager.getConnection("jdbc:sqlite:DBBillet.db");

            PreparedStatement stmt = conn.prepareStatement("UPDATE Billet SET " +
                    "Contenu = ? ,Titre = ? WHERE billetID=?");

            stmt.setString(1,ecrit.getContenu());
            stmt.setString(2, ecrit.getTitre());
            stmt.setInt(3,ecrit.getIdBillet());

            System.out.println( Date.valueOf(ecrit.getDateSoumis()));
            stmt.executeUpdate();
            stmt.close();

        }catch (SQLException e){
            throw new DAOException(e);
        }
        return lire(ecrit.getIdBillet());
    }

    /**
     * Supprime un objet dans la source de données
     *
     * @param objet l'objet à supprimer de la source de données
     * @return l'objet supprimé ou null s'il n'a pas été trouvé
     */
    @Override
    public Billet supprimer(Billet objet) throws DAOException {
        Connection conn;
        Billet billetSupprime;

        try{
            conn = DriverManager.getConnection("jdbc:sqlite:DBBillet.db");
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Billet WHERE billetID=?");
            billetSupprime=lire(objet.getIdBillet());

            stmt.setInt(1, objet.getIdBillet());

            stmt.executeUpdate();
            stmt.close();

        }catch (SQLException e){
            throw new DAOException(e);
        }
        return objet;
    }



    //Lignes qui suivent ne servent que pour tester la bd Billet qui n'est pas finale dans cette version
    public static void main (String []args){
        File bd = new File("DBBillet.db");
        LocalDate dateUn = LocalDate.of(2020, Month.MARCH, 11);
        BilletDAOSQLite daosqLite = new BilletDAOSQLite();
        System.out.println(bd.getAbsolutePath());
        Billet test = null;
        ArrayList<Billet> listeBillet = new ArrayList<Billet>();

        Billet mockBillet = new Billet(1030,"Billet de Java",dateUn,"Ce Billet est un test du DAO",
                LocalDate.parse("2020-03-30"),"DAO","DAOSQL");
        mockBillet.setIdBillet(9029);
        mockBillet.setContenu("Test de modification2");

        try {
            //test = daosqLite.lire(9029);
            //test = daosqLite.creer(mockBillet);
            //daosqLite.supprimer(mockBillet);
            listeBillet = daosqLite.trouverTout();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        //System.out.println(test.toString());

        for(int i=0;i<listeBillet.size();i++){
            System.out.println(listeBillet.get(i));
        }

    }

}
