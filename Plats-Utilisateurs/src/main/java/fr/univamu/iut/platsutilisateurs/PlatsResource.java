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

    @GET
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatsJSON();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getPlat(@PathParam("id") int id) {
        return service.getPlatJSON(id);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createPlat(String platJson) {
        return service.createPlat(platJson);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public String updatePlat(@PathParam("id") int id, String platJson) {
        return service.updatePlat(id, platJson);
    }

    @DELETE
    @Path("/{id}")
    public void deletePlat(@PathParam("id") int id) {
        service.deletePlat(id);
    }
}