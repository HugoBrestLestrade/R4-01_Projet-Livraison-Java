package fr.univamu.iut.platsutilisateurs;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accéder aux plats stockés dans une base de données Mariadb
 */
public class PlatsRepositoryMariadb implements PlatsRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public PlatsRepositoryMariadb(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    /**
     * Méthode fermant le dépôt où sont stockées les informations sur les plats
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
     * Méthode retournant le plat dont l'identifiant est passé en paramètre
     * @param id identifiant du plat recherché
     * @return un objet Plats représentant le plat recherché
     */
    @Override
    public Plats getPlat(int id) {
        Plats selectedPlat = null;
        String query = "SELECT * FROM plats WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");

                selectedPlat = new Plats(id, nom, description, prix);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedPlat;
    }

    /**
     * Méthode retournant la liste des plats
     * @return une liste d'objets plats
     */
    @Override
    public ArrayList<Plats> getAllPlats() {
        ArrayList<Plats> listPlats;
        String query = "SELECT * FROM plats";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            listPlats = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");

                Plats currentPlat = new Plats(id, nom, description, prix);
                listPlats.add(currentPlat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listPlats;
    }

    /**
     * Méthode permettant d'ajouter un nouveau plat
     */
    @Override
    public boolean ajouterPlat(Plats p) {
        String query = "INSERT INTO plats (nom, description, prix) VALUES (?, ?, ?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getPrix());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Méthode permettant de mettre à jour un plat
     */
    @Override
    public boolean updatePlat(int id, Plats p) {
        String query = "UPDATE plats SET nom=?, description=?, prix=? WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getPrix());
            ps.setInt(4, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Méthode permettant de supprimer un plat
     */
    @Override
    public boolean deletePlat(int id) {
        String query = "DELETE FROM plats WHERE id=?";
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