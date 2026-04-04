package fr.univamu.iut.platsutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/plats")
@ApplicationScoped
public class PlatsResource {
    private PlatsService service;

    public PlatsResource(){}

    @Inject
    public PlatsResource(PlatsRepositoryInterface repo) {
        this.service = new PlatsService(repo);
    }

    @GET
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatsJSON();
    }
}