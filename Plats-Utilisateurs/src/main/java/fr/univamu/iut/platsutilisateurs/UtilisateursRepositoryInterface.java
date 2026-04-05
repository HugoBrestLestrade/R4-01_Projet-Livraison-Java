package fr.univamu.iut.platsutilisateurs;
import java.util.ArrayList;

/**
 * Interface d'accès aux données des utilisateurs
 */
public interface UtilisateursRepositoryInterface {

    /**
     * Méthode fermant le dépôt où sont stockées les informations sur les utilisateurs
     */
    public void close();

    /**
     * Méthode retournant l'utilisateur dont l'identifiant est passé en paramètre
     * @param id identifiant de l'utilisateur recherché
     * @return un objet Utilisateurs représentant l'utilisateur recherché
     */
    public Utilisateurs getUtilisateur(int id);

    /**
     * Méthode retournant la liste des utilisateurs
     * @return une liste d'objets utilisateurs
     */
    public ArrayList<Utilisateurs> getAllUtilisateurs();
}