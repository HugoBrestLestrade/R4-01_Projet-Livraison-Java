<?php
/**
 * Modèle représentant un plat proposé par l'entreprise.
 */
class Plat {

    public int    $id;
    public string $nom;
    public string $description;
    public float  $prix;

    public function __construct(int $id, string $nom, string $description, float $prix) {
        $this->id          = $id;
        $this->nom         = $nom;
        $this->description = $description;
        $this->prix        = $prix;
    }

    /**
     * Crée un Plat depuis un tableau associatif (issu du JSON).
     */
    public static function fromArray(array $data): self {
        return new self(
            (int)   $data['id'],
            $data['nom']         ?? 'Sans nom',
            $data['description'] ?? '',
            (float) $data['prix']        ?? 0.0
        );
    }
}
