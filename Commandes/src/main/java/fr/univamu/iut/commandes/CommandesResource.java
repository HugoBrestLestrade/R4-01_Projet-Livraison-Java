package fr.univamu.iut.commandes;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée aux commandes
 * (point d'accès de l'API REST)
 */
@Path("/commandes")
public class CommandesResource {

    /**
     * Service utilisé pour accéder aux données des commandes et récupérer/modifier leurs informations
     */
    private CommandesService service;

    /**
     * Constructeur par défaut
     */
    public CommandesResource() {}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param commandesRepo objet implémentant l'interface d'accès aux données
     */
    public CommandesResource(CommandesRepositoryInterface commandesRepo) {
        this.service = new CommandesService(commandesRepo);
    }

    /**
     * Constructeur permettant d'initialiser le service d'accès aux commandes
     */
    public CommandesResource(CommandesService service) {
        this.service = service;
    }

    /**
     * Endpoint permettant de publier toutes les commandes enregistrées
     * @return la liste des commandes (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllCommandes() {
        return service.getAllCommandesJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'une commande dont l'ID est passé en paramètre dans le chemin
     * @param id identifiant de la commande recherchée
     * @return les informations de la commande recherchée au format JSON
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getCommande(@PathParam("id") int id) {

        String result = service.getCommandeJSON(id);

        // si la commande n'a pas été trouvée
        if (result == null)
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre à jour certaines informations d'une commande
     * @param id l'identifiant de la commande à modifier
     * @param commande la commande transmise en HTTP au format JSON et convertie en objet Commandes
     * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateCommande(@PathParam("id") int id, Commandes commande) {

        // si la commande n'a pas été trouvée ou que la mise à jour a échoué
        if (!service.updateCommande(id, commande))
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }

    /**
     * Endpoint permettant d'ajouter une nouvelle commande complète (avec ses lignes)
     * @param commande la nouvelle commande transmise en HTTP au format JSON
     * @return une réponse "created" avec le code HTTP 201 si l'ajout a réussi
     */
    @POST
    @Consumes("application/json")
    public Response createCommande(Commandes commande) {

        if (service.addCommande(commande)) {
            // Renvoie un code 201 Created, très standard pour les API REST
            return Response.status(Response.Status.CREATED).entity("created").build();
        } else {
            // Renvoie une erreur 500 si l'insertion en base a planté
            throw new InternalServerErrorException("Erreur lors de la création de la commande");
        }
    }
}