package modele;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author socce
 */
public class BilletTest {
    
    public BilletTest() {
    }
    

    /**
     * Test of getTitre method, of class Billet.
     */
    @Test
    public void testGetTitre() {
        System.out.println("getTitre");
        Billet instance = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2019-03-16"),"Danny","Ticketeur TEST");
        String expResult = "Titre Modifie";
        String result = instance.getTitre();
        assertEquals(expResult, result);

    }

    /**
     * Test of getContenu method, of class Billet.
     */
    @Test
    public void testGetContenu() {
        System.out.println("getContenu");
        Billet instance = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2019-03-16"),"Danny","Ticketeur TEST");
        String expResult = "Billet de la methode testModifier()";
        String result = instance.getContenu();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateSoumis method, of class Billet.
     */
    @Test
    public void testGetDateSoumis() {
        System.out.println("getDateSoumis");
        Billet instance = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2020-03-16"),"Danny","Ticketeur TEST");
        LocalDate expResult = LocalDate.parse("2020-03-13");
        LocalDate result = instance.getDateSoumis();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateLimite method, of class Billet.
     */
    @Test
    public void testGetDateLimite() {
        System.out.println("getDateLimite");
        Billet instance = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2020-03-16"),"Danny","Ticketeur TEST");
        LocalDate expResult = LocalDate.parse("2020-03-16");
        LocalDate result = instance.getDateLimite();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdBillet method, of class Billet.
     */
    @Test
    public void testGetIdBillet() {
        System.out.println("getIdBillet");
        Billet instance = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2019-03-16"),"Danny","Ticketeur TEST");
        int expResult = 9029;
        int result = instance.getIdBillet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNomAuteur method, of class Billet.
     */
    @Test
    public void testGetNomAuteur() {
        System.out.println("getNomAuteur");
        Billet instance = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2019-03-16"),"Danny","Ticketeur TEST");
    String expResult = "Danny";
        String result = instance.getNomAuteur();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNomProjet method, of class Billet.
     */
    @Test
    public void testGetNomProjet() {
        System.out.println("getNomProjet");
        Billet instance = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2019-03-16"),"Danny","Ticketeur TEST");
        String expResult = "Ticketeur TEST";
        String result = instance.getNomProjet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTags method, of class Billet.
     */
    @Test
    public void testGetTags() {
        System.out.println("getTags");
        ArrayList<String> lesTags = new ArrayList<String>();
        lesTags.add("Important");
        lesTags.add("Obligatoire");
        Billet instance = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2019-03-16"),"Danny","Ticketeur TEST",lesTags);
        ArrayList<String> expResult = new ArrayList<>(Arrays.asList("Important","Obligatoire"));
        ArrayList<String> result = instance.getTags();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Billet.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Billet mockBillet = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2019-03-16"),"Danny","Ticketeur TEST");
        String expResult = "Numero et titre du Billet: #"+mockBillet.getIdBillet()+" "+mockBillet.getTitre()+" \tDate soumis: "+mockBillet.getDateSoumis() +
                " \nContenu du billet: "+mockBillet.getContenu()+ " \nNom de l'auteur: "+mockBillet.getNomAuteur()+" Nom du projet: "+
                mockBillet.getNomProjet()+" \nDate limite pour completer la tache: "+ mockBillet.getDateLimite()+"\n";
        String result = mockBillet.toString();
        assertEquals(expResult, result);
    }
}
