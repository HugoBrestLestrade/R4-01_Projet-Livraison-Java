package fr.univamu.iut.platsutilisateurs;
import java.util.ArrayList;

/**
 * Interface d'accès aux données des plats
 */
public interface PlatsRepositoryInterface {
    /**
     * Méthode fermant le dépôt où sont stockées les informations sur les plats
     */
    public void close();

    /**
     * Méthode retournant le plat dont l'identifiant est passé en paramètre
     * @param id identifiant du plat recherché
     * @return un objet Plats représentant le plat recherché
     */
    public Plats getPlat(int id);

    /**
     * Méthode retournant la liste des plats
     * @return une liste d'objets plats
     */
    public ArrayList<Plats> getAllPlats();

    /**
     * Méthode permettant d'ajouter un nouveau plat
     * @param p l'objet Plats à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    public boolean ajouterPlat(Plats p);

    /**
     * Méthode permettant de mettre à jour un plat existant
     * @param id identifiant du plat à modifier
     * @param p les nouvelles informations du plat
     * @return true si la modification a réussi, false sinon
     */
    public boolean updatePlat(int id, Plats p);

    /**
     * Méthode permettant de supprimer un plat
     * @param id identifiant du plat à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean deletePlat(int id);
}