package lesClasses;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe représentant un étudiant FISE
 * @author groupe7_4
 * @version 1.0
 */
public class FISE extends Etudiants {
    private int rang;
    private ArrayList<Dominantes> listeDominantes;

    /**
     * Constructeur
     * @param nom
     * @param prenom
     * @param filiere
     * @param promo
     * @param dateNaissance
     * @param username
     * @param password
     * @param rang
     */
   
    public FISE(String nom, String prenom, String filiere, int promo, String dateNaissance,
                String username, String password, int rang) {
        super(nom, prenom, filiere, promo, dateNaissance, username, password);
        this.rang = rang;
        this.listeDominantes = new ArrayList<>();
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    /**
     * Retourne la liste de toutes les dominantes 
     */
    public ArrayList<Dominantes> getListeDominantes() {
        return listeDominantes;
    }

    /**
     * Méthode permettant au FISE de classer ses 5 dominantes préférées
     */
    public void classerDominantes() {
        ArrayList<Dominantes> dominantesDisponibles = recupererDominantes();
        ArrayList<Dominantes> choixEtudiant = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        if (dominantesDisponibles.isEmpty()) {
            System.out.println("Aucune dominante disponible.");
            return;
        }

    
    
}

