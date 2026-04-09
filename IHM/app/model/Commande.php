<?php
/**
 * Modèle représentant une commande passée par un abonné.
 */
class Commande {

    public int    $id;
    public int    $utilisateurId;
    public array  $lignes;
    public string $dateCommande;
    public string $adresseLivraison;
    public string $dateLivraison;
    public float  $prixTotal;

    public function __construct(
        int    $id,
        int    $utilisateurId,
        array  $lignes,
        string $dateCommande,
        string $adresseLivraison,
        string $dateLivraison,
        float  $prixTotal
    ) {
        $this->id               = $id;
        $this->utilisateurId    = $utilisateurId;
        $this->lignes           = $lignes;
        $this->dateCommande     = $dateCommande;
        $this->adresseLivraison = $adresseLivraison;
        $this->dateLivraison    = $dateLivraison;
        $this->prixTotal        = $prixTotal;
    }

    public static function fromArray(array $data): self {
        return new self(
            (int)   $data['id'],
            (int)   $data['utilisateurId']    ?? 0,
            $data['lignes']           ?? [],
            $data['dateCommande']     ?? '',
            $data['adresseLivraison'] ?? '',
            $data['dateLivraison']    ?? '',
            (float) $data['prixTotal']        ?? 0.0
        );
    }
}
