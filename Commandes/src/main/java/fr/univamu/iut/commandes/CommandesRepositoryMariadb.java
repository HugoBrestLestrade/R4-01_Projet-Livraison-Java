package fr.univamu.iut.commandes;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accéder aux commandes stockées dans une base de données Mariadb
 */
public class CommandesRepositoryMariadb implements CommandesRepositoryInterface, Closeable {

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
    public CommandesRepositoryMariadb(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public void close() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Méthode privée utilitaire pour récupérer les lignes associées à une commande
     */
    private ArrayList<LigneCommandes> getLignesPourCommande(int commandeId) {
        ArrayList<LigneCommandes> lignes = new ArrayList<>();
        String query = "SELECT * FROM LigneCommandes WHERE commandeId = ?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, commandeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lignes.add(new LigneCommandes(
                        rs.getInt("menuId"),
                        rs.getString("menuNom"),
                        rs.getInt("quantite"),
                        rs.getDouble("prixUnitaire"),
                        rs.getDouble("prixLigne")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur de chargement des lignes : " + e.getMessage());
        }
        return lignes;
    }

    @Override
    public Commandes getCommande(int id) {
        Commandes selectedCommande = null;
        String query = "SELECT * FROM Commandes WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                int abonneId = result.getInt("abonneId");
                java.util.Date dateCommande = result.getTimestamp("dateCommande");
                String adresseLivraison = result.getString("adresseLivraison");
                java.util.Date dateLivraison = result.getDate("dateLivraison");
                int prixTotal = result.getInt("prixTotal");

                // Récupération de la liste des menus commandés
                ArrayList<LigneCommandes> lesLignes = getLignesPourCommande(id);

                selectedCommande = new Commandes(id, abonneId, dateCommande, adresseLivraison, dateLivraison, lesLignes, prixTotal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedCommande;
    }

    @Override
    public ArrayList<Commandes> getAllCommandes() {
        ArrayList<Commandes> listCommandes = new ArrayList<>();
        String query = "SELECT * FROM Commandes";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                int abonneId = result.getInt("abonneId");
                java.util.Date dateCommande = result.getTimestamp("dateCommande");
                String adresseLivraison = result.getString("adresseLivraison");
                java.util.Date dateLivraison = result.getDate("dateLivraison");
                int prixTotal = result.getInt("prixTotal");

                // Pour chaque commande, on va chercher ses lignes
                ArrayList<LigneCommandes> lesLignes = getLignesPourCommande(id);

                Commandes currentCommande = new Commandes(id, abonneId, dateCommande, adresseLivraison, dateLivraison, lesLignes, prixTotal);
                listCommandes.add(currentCommande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCommandes;
    }

    @Override
    public boolean updateCommande(int id, String adresseLivraison, java.util.Date dateLivraison) {

        String query = "UPDATE Commandes SET adresseLivraison=?, dateLivraison=? WHERE id=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, adresseLivraison);

            if (dateLivraison != null) {
                ps.setDate(2, new java.sql.Date(dateLivraison.getTime()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            ps.setInt(3, id);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }

    public boolean addCommande(Commandes commande) {
        String queryCmd = "INSERT INTO Commandes (id, abonneId, dateCommande, adresseLivraison, dateLivraison, prixTotal) VALUES (?, ?, ?, ?, ?, ?)";
        String queryLigne = "INSERT INTO LigneCommandes (commandeId, menuId, menuNom, quantite, prixUnitaire, prixLigne) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // Début de la transaction
            dbConnection.setAutoCommit(false);

            // Insertion de la commande
            try (PreparedStatement psCmd = dbConnection.prepareStatement(queryCmd)) {
                psCmd.setInt(1, commande.getId());
                psCmd.setInt(2, commande.getAbonneId());
                psCmd.setTimestamp(3, new java.sql.Timestamp(commande.getDateCommande().getTime()));
                psCmd.setString(4, commande.getAdresseLivraison());
                psCmd.setDate(5, new java.sql.Date(commande.getDateLivraison().getTime()));
                psCmd.setDouble(6, commande.getPrixTotal());

                psCmd.executeUpdate();
            }

            // Insertion des lignes
            try (PreparedStatement psLigne = dbConnection.prepareStatement(queryLigne)) {
                for (LigneCommandes ligne : commande.getLignes()) {
                    psLigne.setInt(1, commande.getId());
                    psLigne.setInt(2, ligne.getMenuId());
                    psLigne.setString(3, ligne.getMenuNom());
                    psLigne.setInt(4, ligne.getQuantite());
                    psLigne.setDouble(5, ligne.getPrixUnitaire());
                    psLigne.setDouble(6, ligne.getPrixLigne());

                    psLigne.executeUpdate();
                }
            }

            // Validation de la transaction
            dbConnection.commit();
            return true;

        } catch (SQLException e) {
            try {
                dbConnection.rollback(); // Annulation en cas d'erreur
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return false;
        } finally {
            try {
                dbConnection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}