package fr.univamu.iut.platsutilisateurs;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource Utilisateurs
 */
public class UtilisateursService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les utilisateurs
     */
    protected UtilisateursRepositoryInterface utilisateursRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param utilisateursRepo objet implémentant l'interface d'accès aux données
     */
    public UtilisateursService(UtilisateursRepositoryInterface utilisateursRepo) {
        this.utilisateursRepo = utilisateursRepo;
    }

    /**
     * Méthode retournant les informations sur les utilisateurs au format JSON
     * @return une chaîne de caractères contenant les informations au format JSON
     */
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

    /**
     * Méthode retournant au format JSON les informations sur un utilisateur recherché
     * @param id l'identifiant de l'utilisateur recherché
     * @return une chaîne de caractères contenant les informations au format JSON
     */
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

    /**
     * Méthode permettant de créer un nouvel utilisateur à partir d'une chaîne JSON
     * @param utilisateurJson chaîne JSON représentant le nouvel utilisateur
     * @return la chaîne JSON de l'utilisateur créé (pour confirmer la création)
     */
    public String createUtilisateur(String utilisateurJson) {
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            Utilisateurs nouvelUtilisateur = jsonb.fromJson(utilisateurJson, Utilisateurs.class);

            boolean isCreated = utilisateursRepo.ajouterUtilisateur(nouvelUtilisateur);

            if (isCreated) {
                result = jsonb.toJson(nouvelUtilisateur);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jour un utilisateur existant
     * @param id identifiant de l'utilisateur à modifier
     * @param utilisateurJson chaîne JSON contenant les nouvelles informations
     * @return la chaîne JSON de l'utilisateur mis à jour
     */
    public String updateUtilisateur(int id, String utilisateurJson) {
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            Utilisateurs utilisateurMaj = jsonb.fromJson(utilisateurJson, Utilisateurs.class);
            utilisateurMaj.setId(id);

            boolean isUpdated = utilisateursRepo.updateUtilisateur(id, utilisateurMaj);

            if (isUpdated) {
                result = jsonb.toJson(utilisateurMaj);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Méthode permettant de supprimer un utilisateur
     * @param id identifiant de l'utilisateur à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean deleteUtilisateur(int id) {
        boolean isDeleted = false;
        try {
            isDeleted = utilisateursRepo.deleteUtilisateur(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return isDeleted;
    }
}