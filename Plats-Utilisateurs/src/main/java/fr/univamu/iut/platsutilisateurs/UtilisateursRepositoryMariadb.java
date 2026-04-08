package fr.univamu.iut.platsutilisateurs;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accéder aux utilisateurs stockés dans une base de données Mariadb
 */
public class UtilisateursRepositoryMariadb implements UtilisateursRepositoryInterface, Closeable {

    /**
     * Accès à la base de données
     */
    protected Connection dbConnection;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public UtilisateursRepositoryMariadb(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    /**
     * Méthode fermant le dépôt où sont stockées les informations sur les utilisateurs
     */
    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Méthode retournant l'utilisateur dont l'identifiant est passé en paramètre
     * @param id identifiant de l'utilisateur recherché
     * @return un objet Utilisateurs représentant l'utilisateur recherché
     */
    @Override
    public Utilisateurs getUtilisateur(int id) {
        Utilisateurs selectedUtilisateur = null;
        String query = "SELECT * FROM utilisateurs WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adresse = result.getString("adresse");
                String email = result.getString("email");
                String mdp = result.getString("mdp");

                selectedUtilisateur = new Utilisateurs(id, nom, prenom, adresse, email, mdp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUtilisateur;
    }

    /**
     * Méthode retournant la liste des utilisateurs
     * @return une liste d'objets utilisateurs
     */
    @Override
    public ArrayList<Utilisateurs> getAllUtilisateurs() {
        ArrayList<Utilisateurs> listUtilisateurs;
        String query = "SELECT * FROM utilisateurs";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            listUtilisateurs = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adresse = result.getString("adresse");
                String email = result.getString("email");
                String mdp = result.getString("mdp");

                Utilisateurs currentUtilisateur = new Utilisateurs(id, nom, prenom, adresse, email, mdp);
                listUtilisateurs.add(currentUtilisateur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUtilisateurs;
    }

    /**
     * Méthode permettant d'ajouter un nouvel utilisateur (CREATE)
     */
    @Override
    public boolean ajouterUtilisateur(Utilisateurs u) {
        String query = "INSERT INTO utilisateurs (nom, prenom, adresse, email, mdp) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getAdresse());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getMdp());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Méthode permettant de mettre à jour un utilisateur (UPDATE)
     */
    @Override
    public boolean updateUtilisateur(int id, Utilisateurs u) {
        String query = "UPDATE utilisateurs SET nom=?, prenom=?, adresse=?, email=?, mdp=? WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getAdresse());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getMdp());
            ps.setInt(6, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Méthode permettant de supprimer un utilisateur (DELETE)
     */
    @Override
    public boolean deleteUtilisateur(int id) {
        String query = "DELETE FROM utilisateurs WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}