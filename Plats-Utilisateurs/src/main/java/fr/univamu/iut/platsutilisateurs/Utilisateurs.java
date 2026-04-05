package fr.univamu.iut.platsutilisateurs;

/**
 * Classe représentant un utilisateur
 */
public class Utilisateurs {

    /**
     * Identifiant de l'utilisateur
     */
    protected int id;

    /**
     * Nom de l'utilisateur
     */
    protected String nom;

    /**
     * Prénom de l'utilisateur
     */
    protected String prenom;

    /**
     * Adresse de l'utilisateur
     */
    protected String adresse;

    /**
     * Adresse email de l'utilisateur
     */
    protected String email;

    /**
     * Mot de passe de l'utilisateur
     */
    protected String mdp;

    /**
     * Constructeur par défaut
     */
    public Utilisateurs() {}

    /**
     * Constructeur d'utilisateur
     * @param id identifiant de l'utilisateur
     * @param nom nom de l'utilisateur
     * @param prenom prénom de l'utilisateur
     * @param adresse adresse de l'utilisateur
     * @param email adresse email de l'utilisateur
     * @param mdp mot de passe de l'utilisateur
     */
    public Utilisateurs(int id, String nom, String prenom, String adresse, String email, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.mdp = mdp;
    }

    /**
     * Méthode permettant d'accéder à l'identifiant de l'utilisateur
     * @return un entier avec l'identifiant de l'utilisateur
     */
    public int getId() {
        return id;
    }

    /**
     * Méthode permettant de modifier l'identifiant de l'utilisateur
     * @param id un entier avec l'identifiant à utiliser
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Méthode permettant d'accéder au nom de l'utilisateur
     * @return une chaîne de caractères avec le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant de modifier le nom de l'utilisateur
     * @param nom une chaîne de caractères avec le nom à utiliser
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode permettant d'accéder au prénom de l'utilisateur
     * @return une chaîne de caractères avec le prénom de l'utilisateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Méthode permettant de modifier le prénom de l'utilisateur
     * @param prenom une chaîne de caractères avec le prénom à utiliser
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Méthode permettant d'accéder à l'adresse de l'utilisateur
     * @return une chaîne de caractères avec l'adresse de l'utilisateur
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Méthode permettant de modifier l'adresse de l'utilisateur
     * @param adresse une chaîne de caractères avec l'adresse à utiliser
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Méthode permettant d'accéder à l'email de l'utilisateur
     * @return une chaîne de caractères avec l'email de l'utilisateur
     */
    public String getEmail() {
        return email;
    }

    /**
     * Méthode permettant de modifier l'email de l'utilisateur
     * @param email une chaîne de caractères avec l'email à utiliser
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Méthode permettant d'accéder au mot de passe de l'utilisateur
     * @return une chaîne de caractères avec le mot de passe de l'utilisateur
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Méthode permettant de modifier le mot de passe de l'utilisateur
     * @param mdp une chaîne de caractères avec le mot de passe à utiliser
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}