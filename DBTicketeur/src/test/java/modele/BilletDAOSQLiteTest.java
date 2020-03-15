package modele;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author socce
 */
public class BilletDAOSQLiteTest {
    
    public BilletDAOSQLiteTest() {
    }
    

    /**
     * Test of lire method, of class BilletDAOSQLite.
     */
    @Test
    public void testLire() throws Exception {
        System.out.println("Test de la methode lire: \n");
        BilletDAOSQLite instance = new BilletDAOSQLite();
        Billet expResult = new Billet(1,"Billet num:1", LocalDate.parse("2020-03-13"),
                "qymic\n" +
                        "yu",LocalDate.parse("2020-03-16"),"Danny","Ticketeur");
        Billet result = instance.lire(1);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of creer method, of class BilletDAOSQLite.
     */
    @Test
    public void testCreer() throws Exception {
        System.out.println("Test de la methode creer: \n");
        BilletDAOSQLite instance = new BilletDAOSQLite();
        Billet expResult = new Billet(9029,"Billet num:30", LocalDate.parse("2020-03-13"),
                "Billet de la methode testCreer()",LocalDate.parse("2020-03-16"),"Danny","Ticketeur TEST");
        Billet result = instance.creer(expResult);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of modifier method, of class BilletDAOSQLite.
     */
    @Test
    public void testModifier() throws Exception {
        System.out.println("Test de la methode modifier: \n");
        BilletDAOSQLite instance = new BilletDAOSQLite();
        Billet expResult = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2020-03-16"),"Danny","Ticketeur TEST");

        Billet mockBillet= new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-26"),
                "Billet de la methode testModifier()",LocalDate.parse("2019-03-25"),"","");
        Billet result = instance.modifier(mockBillet);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of supprimer method, of class BilletDAOSQLite.
     */
    @Test
    public void testSupprimer() throws Exception {
        System.out.println("Test de la methode supprimer: \n");
        BilletDAOSQLite instance = new BilletDAOSQLite();
        Billet mockBillet = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2019-03-16"),"Danny","Ticketeur TEST");

        Billet expResult = new Billet(9029,"Titre Modifie", LocalDate.parse("2020-03-13"),
                "Billet de la methode testModifier()",LocalDate.parse("2019-03-16"),"Danny","Ticketeur TEST");
        Billet result = instance.supprimer(mockBillet);
        assertEquals(expResult.toString(), result.toString());
    }

}
