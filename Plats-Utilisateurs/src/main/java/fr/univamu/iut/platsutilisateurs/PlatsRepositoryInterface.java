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
}