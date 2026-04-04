package fr.univamu.iut.platsutilisateurs;
import java.util.ArrayList;

public interface UtilisateursRepositoryInterface {
    public void close();
    public Utilisateurs getUtilisateur(int id);
    public ArrayList<Utilisateurs> getAllUtilisateurs();
}