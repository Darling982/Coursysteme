package dao;
import java.sql.*;
import java.util.ArrayList;

import lesClasses.Dominantes;
/**
 * Classe permettant l'accès aux dominantes dans la base de donnée 
 * 
 * @author Groupe7_4
 * @version 1.0
 * 
 */

public class DominantesDAO extends ConnectionDAO {
   
	private Connection con;
	/**
	 * Constructor
	 * 
	 */
	public DominantesDAO()  {
		
		super(); //appel du constructeur de ConnectionDAO
	}
	 
	 /**
     * Récupération de la liste des dominantes de  la base de données
     */
    public ArrayList<Dominantes> getToutesDominantes() {
        ArrayList<Dominantes> dominantes = new ArrayList<>();
        String sql = "SELECT * FROM Dominante";
        try (PreparedStatement statement = con.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

               while (resultSet.next())
               {
                   String nom = resultSet.getString("nom");
                   int placesDispo = resultSet.getInt("placesDispo");
                   int placesPrises = resultSet.getInt("placesPrises");
                   Dominantes dominante = new Dominantes(nom, placesDispo, placesPrises);
                   dominantes.add(dominante);
	
               }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dominantes;
    
	
     }
               /**
           	 * Permet d'ajouter une dominante dans la table dominantes.
           	 * Le mode est auto-commit par defaut : chaque insertion est validee
           	 * 
           	 * @param domin la dominante à ajouter
           	 * @return retourne le nombre de lignes ajoutees dans la table
           	 */
             
  
 }


        
