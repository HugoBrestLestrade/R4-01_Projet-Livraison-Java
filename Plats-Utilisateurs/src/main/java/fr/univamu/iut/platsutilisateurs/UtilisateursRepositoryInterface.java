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

    /**
     * Méthode permettant d'ajouter un nouvel utilisateur
     * @param u l'objet Utilisateurs à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    public boolean ajouterUtilisateur(Utilisateurs u);

    /**
     * Méthode permettant de mettre à jour un utilisateur existant
     * @param id identifiant de l'utilisateur à modifier
     * @param u les nouvelles informations de l'utilisateur
     * @return true si la modification a réussi, false sinon
     */
    public boolean updateUtilisateur(int id, Utilisateurs u);

    /**
     * Méthode permettant de supprimer un utilisateur
     * @param id identifiant de l'utilisateur à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean deleteUtilisateur(int id);
}