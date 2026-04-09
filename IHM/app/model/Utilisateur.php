<?php
/**
 * Modèle représentant un abonné de l'application.
 */
class Utilisateur {

    public int    $id;
    public string $nom;
    public string $email;

    public function __construct(int $id, string $nom, string $email) {
        $this->id    = $id;
        $this->nom   = $nom;
        $this->email = $email;
    }

    public static function fromArray(array $data): self {
        return new self(
            (int) $data['id'],
            $data['nom']   ?? '',
            $data['email'] ?? ''
        );
    }
}
