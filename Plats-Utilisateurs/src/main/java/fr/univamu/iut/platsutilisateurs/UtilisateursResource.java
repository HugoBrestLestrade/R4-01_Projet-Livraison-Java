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

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getUtilisateur(@PathParam("id") int id) {
        return service.getUtilisateurJSON(id);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createUtilisateur(String utilisateurJson) {
        return service.createUtilisateur(utilisateurJson);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public String updateUtilisateur(@PathParam("id") int id, String utilisateurJson) {
        return service.updateUtilisateur(id, utilisateurJson);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUtilisateur(@PathParam("id") int id) {
        service.deleteUtilisateur(id);
    }
}