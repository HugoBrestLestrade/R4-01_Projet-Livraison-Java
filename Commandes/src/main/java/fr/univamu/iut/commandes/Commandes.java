package fr.univamu.iut.commandes;

import java.util.ArrayList;
import java.util.Date;

public class Commandes {
    protected int id;

    protected int abonneId;

    protected Date dateCommande;

    protected String adresseLivraison;

    protected Date dateLivraison;

    protected ArrayList<LigneCommandes> lignes;

    protected int prixTotal;

    public Commandes(){}

    public Commandes(int id, int abonneId, Date dateCommande, String adresseLivraison, Date dateLivraison, ArrayList<LigneCommandes> lignes, int prixTotal) {
        this.id = id;
        this.abonneId = abonneId;
        this.dateCommande = dateCommande;
        this.adresseLivraison = adresseLivraison;
        this.dateLivraison = dateLivraison;
        this.lignes = lignes;
        this.prixTotal = prixTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAbonneId() {
        return abonneId;
    }

    public void setAbonneId(int abonneId) {
        this.abonneId = abonneId;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public ArrayList<LigneCommandes> getLignes() {
        return lignes;
    }

    public void setLignes(ArrayList<LigneCommandes> lignes) {
        this.lignes = lignes;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    @Override
    public String toString() {
        return "Commandes{" +
                "id=" + id +
                ", abonneId=" + abonneId +
                ", dateCommande=" + dateCommande +
                ", adresseLivraison='" + adresseLivraison + '\'' +
                ", dateLivraison=" + dateLivraison +
                ", lignes=" + lignes +
                ", prixTotal=" + prixTotal +
                '}';
    }


}
