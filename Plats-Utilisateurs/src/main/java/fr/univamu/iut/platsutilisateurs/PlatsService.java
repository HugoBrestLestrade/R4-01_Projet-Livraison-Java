package fr.univamu.iut.platsutilisateurs;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource Plats
 */
public class PlatsService {

    protected PlatsRepositoryInterface platsRepo;

    public PlatsService(PlatsRepositoryInterface platsRepo) {
        this.platsRepo = platsRepo;
    }

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