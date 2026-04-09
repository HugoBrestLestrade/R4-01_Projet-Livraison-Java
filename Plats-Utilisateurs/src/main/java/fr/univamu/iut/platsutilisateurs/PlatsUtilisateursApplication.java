package fr.univamu.iut.platsutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class PlatsUtilisateursApplication extends Application {

    /**
     * Produit l'injection pour les Plats
     */
    @Produces
    private PlatsRepositoryInterface openPlatsDbConnection() {
        PlatsRepositoryMariadb db = null;
        try {
            db = new PlatsRepositoryMariadb("jdbc:mariadb://mysql-secondeenfance.alwaysdata.net/secondeenfance_platsutilisateurs_db", "secondeenfance_platsutilisateurs", "JX>t^2gmc'bZAPE");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Ferme la connexion des Plats
     */
    private void closePlatsDbConnection(@Disposes PlatsRepositoryInterface platsRepo) {
        platsRepo.close();
    }

    /**
     * Produit l'injection pour les Utilisateurs
     */
    @Produces
    private UtilisateursRepositoryInterface openUtilisateursDbConnection() {
        UtilisateursRepositoryMariadb db = null;
        try {
            db = new UtilisateursRepositoryMariadb("jdbc:mariadb://mysql-secondeenfance.alwaysdata.net/secondeenfance_platsutilisateurs_db", "secondeenfance_platsutilisateurs", "JX>t^2gmc'bZAPE");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Ferme la connexion des Utilisateurs
     */
    private void closeUtilisateursDbConnection(@Disposes UtilisateursRepositoryInterface utilisateursRepo) {
        utilisateursRepo.close();
    }
}