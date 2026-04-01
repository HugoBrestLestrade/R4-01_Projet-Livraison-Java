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
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface BookRepositoryInterface utilisée
     *          pour accéder aux données des livres, voire les modifier
     */
    @Produces
    private PlatsUtilisateursRepositoryInterface openDbConnection(){
        PlatsUtilisateursRepositoryMariadb db = null;

        try{
            db = new PlatsUtilisateursRepositoryMariadb("jdbc:mariadb://mysql-secondeenfance.alwaysdata.net/secondeenfance_platsutilisateurs_db", "secondeenfance_platsutilisateurs ", "JX>t^2gmc'bZAPE");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param platsutilisateursrepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes PlatsUtilisateursRepositoryInterface platsutilisateursrepo ) {
        platsutilisateursrepo.close();
    }
}
