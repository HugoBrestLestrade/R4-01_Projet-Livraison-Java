package fr.univamu.iut.platsutilisateurs;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accéder aux utilisateurs stockés dans une base de données Mariadb
 */
public class UtilisateursRepositoryMariadb implements UtilisateursRepositoryInterface, Closeable {

    protected Connection dbConnection;

    public UtilisateursRepositoryMariadb(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

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
}