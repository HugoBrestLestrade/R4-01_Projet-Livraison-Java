<?php
require_once __DIR__ . '/ApiClient.php';
require_once __DIR__ . '/../model/Plat.php';
require_once __DIR__ . '/../model/Utilisateur.php';

class PlatsUtilisateursService {

    private string $baseUrl = 'http://localhost:3003';
    private string $jsonPath;

    public function __construct() {
        $this->jsonPath = __DIR__ . '/../../json/plats-utilisateurs.json';
    }

    /**
     * Tente l'API, retombe sur le JSON local si indisponible.
     * @param string $endpoint  ex: '/plats', '/utilisateurs'
     * @param string $jsonKey   clé dans le JSON local ex: 'plats', 'utilisateurs'
     */
    private function fetch(string $endpoint, string $jsonKey): array {

        $data = ApiClient::get($this->baseUrl . $endpoint);
        if (!empty($data)) return $data;


        if (!file_exists($this->jsonPath)) return [];
        $contenu = file_get_contents($this->jsonPath);
        $local = json_decode($contenu, true) ?? [];

        return $local[$jsonKey] ?? [];
    }

    public function getAllPlats(): array {
        $liste = $this->fetch('/plats', 'plats');
        return array_map(fn($item) => Plat::fromArray($item), $liste);
    }

    public function getPlatById(int $id): ?Plat {

        $data = ApiClient::get($this->baseUrl . '/plats/' . $id);
        if (!empty($data)) return Plat::fromArray($data);


        $tous = $this->fetch('/plats', 'plats');
        foreach ($tous as $item) {
            if ($item['id'] === $id) return Plat::fromArray($item);
        }
        return null;
    }

    public function getAllUtilisateurs(): array {
        $liste = $this->fetch('/utilisateurs', 'utilisateurs');
        return array_map(fn($item) => Utilisateur::fromArray($item), $liste);
    }
}