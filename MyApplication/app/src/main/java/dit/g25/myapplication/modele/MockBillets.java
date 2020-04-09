package dit.g25.myapplication.modele;

import java.time.LocalDate;
import java.time.Month;

public class MockBillets {

    LocalDate dateUn = LocalDate.of(2020, Month.MARCH, 11);
    LocalDate dateDeux = LocalDate.of(2020, Month.MARCH, 16);

    Billet mockBillet1 = new Billet(9029,"TEST", dateUn , "Mock Billet 1",
            dateDeux, "Danny Villeda","Ticketeur" );

    Billet mockBillet2 = new Billet(0303,"TES2", dateUn , "Mock Billet 2",
            dateDeux, "Danny Villeda","Ticketeur" );

    Billet mockBillet3 = new Billet(1042,"TEST3", dateUn , "Mock Billet 3",
            dateDeux, "Danny Villeda","Ticketeur" );
}
