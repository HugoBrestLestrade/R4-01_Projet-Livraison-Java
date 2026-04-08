package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configuration principale de l'application REST
 * L'annotation @ApplicationPath définit l'URL de base de toutes vos routes (ex: http://localhost:8080/api/...)
 */
@ApplicationPath("/api")
@ApplicationScoped
public class CommandesApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connexion à la base de données au moment de la création
     * de la ressource (CommandesResource).
     * * L'annotation @Produces indique que cette méthode "fabrique" l'objet qui sera injecté.
     * * @return un objet implémentant l'interface CommandesRepositoryInterface utilisée
     * pour accéder aux données des commandes
     */
    @Produces
    private CommandesRepositoryInterface openDbConnection() {
        CommandesRepositoryMariadb db = null;

        try {
            // Attention : Pensez à remplacer [compte] et mdp par vos vrais identifiants AlwaysData ou locaux
            db = new CommandesRepositoryMariadb(
                    "jdbc:mariadb://mysql-architecturelogicielle-willemchetioui.alwaysdata.net/architecturelogicielle-willemchetioui_commandes_db",
                    "453351_annonces",
                    "Willem22"
            );
        } catch (Exception e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer proprement la connexion à la base de données lorsque l'application est arrêtée.
     * * L'annotation @Disposes demande à CDI d'appeler cette méthode automatiquement lors de la destruction
     * de l'objet créé par @Produces.
     * * @param commandesRepo la connexion à la base de données instanciée dans la méthode openDbConnection
     */
    private void closeDbConnection(@Disposes CommandesRepositoryInterface commandesRepo) {
        if (commandesRepo != null) {
            commandesRepo.close();
            System.out.println("Connexion à la base de données fermée proprement.");
        }
    }
}