<?php
/**
 * Modèle représentant un menu composé de plats.
 */
class Menu {

    public int    $id;
    public string $nom;
    public string $createur;
    public string $dateCreation;
    public array  $platsIds;

    public function __construct(
        int    $id,
        string $nom,
        string $createur,
        string $dateCreation,
        array  $platsIds
    ) {
        $this->id           = $id;
        $this->nom          = $nom;
        $this->createur     = $createur;
        $this->dateCreation = $dateCreation;
        $this->platsIds     = $platsIds;
    }

    public static function fromArray(array $data): self {
        return new self(
            (int)  $data['id'],
            $data['nom']          ?? 'Menu sans nom',
            $data['createur']     ?? '',
            $data['dateCreation'] ?? '',
            $data['platsIds']     ?? []
        );
    }
}
