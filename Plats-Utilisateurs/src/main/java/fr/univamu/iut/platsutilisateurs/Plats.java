package fr.univamu.iut.platsutilisateurs;

/**
 * Classe représentant un ou des plats
 */
public class Plats {

    /**
     * Identifiant du plat
     */
    protected int id;

    /**
     * Nom du plat
     */
    protected String nom;

    /**
     * Description du plat
     */
    protected String description;

    /**
     * Prix du plat
     */
    protected double prix;

    /**
     * Constructeur par défaut
     */
    public Plats() {
    }

    /**
     * Constructeur de plats
     * @param id identifiant du plat
     * @param nom nom du plat
     * @param description description du plat
     * @param prix prix du plat
     */
    public Plats(int id, String nom, String description, double prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    /**
     * Méthode permettant d'accéder à l'identifiant du plat
     * @return un entier avec l'identifiant du plat
     */
    public int getId() {
        return id;
    }

    /**
     * Méthode permettant d'accéder au nom du plat
     * @return une chaîne de caractères avec le nom du plat
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant d'accéder à la description du plat
     * @return une chaîne de caractères avec la description du plat
     */
    public String getDescription() {
        return description;
    }

    /**
     * Méthode permettant d'accéder au prix du plat
     * @return un décimal (double) avec le prix du plat
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Méthode permettant de modifier l'identifiant du plat
     * @param id un entier avec l'identifiant à utiliser
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Méthode permettant de modifier le nom du plat
     * @param nom une chaîne de caractères avec le nom à utiliser
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode permettant de modifier la description du plat
     * @param description une chaîne de caractères avec la description à utiliser
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Méthode permettant de modifier le prix du plat
     * @param prix un décimal (double) avec le prix à utiliser
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Plats{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}