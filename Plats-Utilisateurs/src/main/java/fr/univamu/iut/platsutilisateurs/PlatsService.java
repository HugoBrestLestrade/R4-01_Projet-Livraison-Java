package fr.univamu.iut.platsutilisateurs;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource Plats
 */
public class PlatsService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les plats
     */
    protected PlatsRepositoryInterface platsRepo;


    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param platsRepo objet implémentant l'interface d'accès aux données
     */
    public PlatsService(PlatsRepositoryInterface platsRepo) {
        this.platsRepo = platsRepo;
    }

    /**
     * Méthode retournant les informations sur les plats au format JSON
     * @return une chaîne de caractères contenant les informations au format JSON
     */
    public String getAllPlatsJSON() {
        ArrayList<Plats> allPlats = platsRepo.getAllPlats();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allPlats);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un plat recherché
     * @param id l'identifiant du plat recherché
     * @return une chaîne de caractères contenant les informations au format JSON
     */
    public String getPlatJSON(int id) {
        String result = null;
        Plats myPlat = platsRepo.getPlat(id);

        if (myPlat != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myPlat);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de créer un nouveau plat à partir d'une chaîne JSON
     * @param platJson chaîne JSON représentant le nouveau plat
     * @return la chaîne JSON du plat créé
     */
    public String createPlat(String platJson) {
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            Plats nouveauPlat = jsonb.fromJson(platJson, Plats.class);
            boolean isCreated = platsRepo.ajouterPlat(nouveauPlat);

            if (isCreated) {
                result = jsonb.toJson(nouveauPlat);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jour un plat existant
     * @param id identifiant du plat à modifier
     * @param platJson chaîne JSON contenant les nouvelles informations
     * @return la chaîne JSON du plat mis à jour
     */
    public String updatePlat(int id, String platJson) {
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            Plats platMaj = jsonb.fromJson(platJson, Plats.class);
            platMaj.setId(id);

            boolean isUpdated = platsRepo.updatePlat(id, platMaj);

            if (isUpdated) {
                result = jsonb.toJson(platMaj);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Méthode permettant de supprimer un plat
     * @param id identifiant du plat à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean deletePlat(int id) {
        boolean isDeleted = false;
        try {
            isDeleted = platsRepo.deletePlat(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return isDeleted;
    }
}