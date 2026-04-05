package fr.univamu.iut.platsutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

/**
 * Ressource associée aux utilisateurs
 * (point d'accès de l'API REST)
 */
@Path("/utilisateurs")
@ApplicationScoped
public class UtilisateursResource {

    /**
     * Service utilisé pour accéder aux données des utilisateurs et récupérer/modifier leurs informations
     */
    private UtilisateursService service;

    /**
     * Constructeur par défaut
     */
    public UtilisateursResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param repo objet implémentant l'interface d'accès aux données
     */
    @Inject
    public UtilisateursResource(UtilisateursRepositoryInterface repo) {
        this.service = new UtilisateursService(repo);
    }

    /**
     * Endpoint permettant de publier tous les utilisateurs enregistrés
     * @return la liste des utilisateurs (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllUtilisateurs() {
        return service.getAllUtilisateursJSON();
    }
}