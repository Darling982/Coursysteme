package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Classe permettant l'accès à la base de donnée
 * 
 * @author Groupe7_4
 * @version 1.0
 */

public class ConnectionDAO {
	
	protected Connection con;
	/**
	 * Constructeur
	 * Connexion à la base de donnée 
	 * URL,LOGIN,PASS sont des constantes 
	 */
		
		// machine de l'école donc ...
	final static String URL  = "jdbc:oracle:thin:@//192.168.4.183/orcl.intranet.int";

    final static String LOGIN = "C##BDD7_4";   // nom de conexion 
	final static String PASS  = "BDD74";   // mot de passe de la conexion
	
	public ConnectionDAO() {
		// chargement du pilote de bases de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN,PASS);
			
		} catch (SQLException e) {
			 e.printStackTrace();;
		}
	}

	
}
