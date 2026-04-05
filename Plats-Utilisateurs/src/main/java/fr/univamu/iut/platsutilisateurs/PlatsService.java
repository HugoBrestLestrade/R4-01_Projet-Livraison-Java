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
}