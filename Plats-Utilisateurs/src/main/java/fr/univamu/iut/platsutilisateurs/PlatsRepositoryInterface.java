package fr.univamu.iut.platsutilisateurs;
import java.util.ArrayList;

public interface PlatsRepositoryInterface {
    public void close();
    public Plats getPlat(int id);
    public ArrayList<Plats> getAllPlats();
}