package fr.univamu.iut.commandes;

import java.util.ArrayList;
import java.util.Date;

/**
 * Interface d'accès aux données des commandes
 */
public interface CommandesRepositoryInterface {

    /**
     * Méthode fermant le dépôt où sont stockées les informations sur les commandes
     */
    public void close();

    /**
     * Méthode retournant la commande dont l'identifiant est passé en paramètre
     * @param id identifiant unique de la commande recherchée
     * @return un objet Commandes représentant la commande recherchée (ou null si non trouvée)
     */
    public Commandes getCommande(int id);

    /**
     * Méthode retournant la liste de toutes les commandes
     * @return une liste d'objets Commandes
     */
    public ArrayList<Commandes> getAllCommandes();

    /**
     * Méthode permettant de mettre à jour une commande enregistrée.
     * (Ici, on propose la mise à jour de l'adresse et de la date de livraison à titre d'exemple)
     * * @param id identifiant de la commande à mettre à jour
     * @param adresseLivraison nouvelle adresse de livraison
     * @param dateLivraison nouvelle date de livraison prévue
     * @return true si la commande existe et la mise à jour a été faite, false sinon
     */
    public boolean updateCommande(int id, String adresseLivraison, Date dateLivraison);

    /**
     * Méthode permettant d'ajouter une nouvelle commande dans la base de données
     * @param commande la commande à insérer (avec ses lignes)
     * @return true si l'insertion a réussi, false sinon
     */
    public boolean addCommande(Commandes commande);
}
