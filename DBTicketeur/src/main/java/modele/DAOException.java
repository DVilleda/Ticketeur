package modele;

/**
 * Une exception pouvant survenir lors de l'accès à une source de données
 *
 */
public class DAOException extends Exception{
    public Exception exceptionOriginale;

    public DAOException(Exception ex){
        exceptionOriginale=ex;
    }
}
