package dit.g25.myapplication.modele;

import java.util.ArrayList;

public abstract class BilletDAO extends DAO<Billet>{

    public abstract ArrayList<Billet> trouverTout() throws DAOException;

}
