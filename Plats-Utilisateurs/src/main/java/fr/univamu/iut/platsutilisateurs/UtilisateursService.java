package fr.univamu.iut.platsutilisateurs;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource Utilisateurs
 */
public class UtilisateursService {

    protected UtilisateursRepositoryInterface utilisateursRepo;

    public UtilisateursService(UtilisateursRepositoryInterface utilisateursRepo) {
        this.utilisateursRepo = utilisateursRepo;
    }

    public String getAllUtilisateursJSON() {
        ArrayList<Utilisateurs> allUtilisateurs = utilisateursRepo.getAllUtilisateurs();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUtilisateurs);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String getUtilisateurJSON(int id) {
        String result = null;
        Utilisateurs myUtilisateur = utilisateursRepo.getUtilisateur(id);

        if (myUtilisateur != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUtilisateur);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }
}