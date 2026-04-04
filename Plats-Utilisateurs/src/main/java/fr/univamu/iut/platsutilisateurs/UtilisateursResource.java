package fr.univamu.iut.platsutilisateurs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/utilisateurs")
@ApplicationScoped
public class UtilisateursResource {
    private UtilisateursService service;

    public UtilisateursResource(){}

    @Inject
    public UtilisateursResource(UtilisateursRepositoryInterface repo) {
        this.service = new UtilisateursService(repo);
    }

    @GET
    @Produces("application/json")
    public String getAllUtilisateurs() {
        return service.getAllUtilisateursJSON();
    }
}