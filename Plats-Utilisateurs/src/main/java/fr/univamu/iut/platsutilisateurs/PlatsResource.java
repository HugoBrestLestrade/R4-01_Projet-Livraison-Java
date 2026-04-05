package fr.univamu.iut.platsutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

/**
 * Ressource associée aux plats
 * (point d'accès de l'API REST)
 */
@Path("/plats")
@ApplicationScoped
public class PlatsResource {
    /**
     * Service utilisé pour accéder aux données des plats et récupérer/modifier leurs informations
     */
    private PlatsService service;

    /**
     * Constructeur par défaut
     */
    public PlatsResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param repo objet implémentant l'interface d'accès aux données
     */
    @Inject
    public PlatsResource(PlatsRepositoryInterface repo) {
        this.service = new PlatsService(repo);
    }

    /**
     * Endpoint permettant de publier tous les plats enregistrés
     * @return la liste des plats (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatsJSON();
    }
}