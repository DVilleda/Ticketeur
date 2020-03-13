package modele;

import java.io.File;
import java.sql.*;

/**
 * Factory permettant d'obtenir une connexion à une DB SQLite
 *
 */
public class SQLConnectionFactory {
    private static Connection cnx;

    private SQLConnectionFactory(){}

    public static Connection getConnection() throws SQLException{
        if(cnx==null || cnx.isClosed()) cnx=DriverManager.getConnection("jdbc:sqlite:DBBillet.db");
        return cnx;
    }
}
