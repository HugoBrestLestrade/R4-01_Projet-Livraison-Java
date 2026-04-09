<?php
require_once __DIR__ . '/../model/Commande.php';

class CommandeService {

    private string $jsonPath;

    public function __construct() {
        $this->jsonPath = __DIR__ . '/../../json/commandes.json';
    }

    public function getAllCommandes(): array {
        if (!file_exists($this->jsonPath)) return [];
        $local = json_decode(file_get_contents($this->jsonPath), true) ?? [];
        return array_map(fn($item) => Commande::fromArray($item), $local['commandes'] ?? []);
    }

    public function createCommande(array $commandeData): array {
        $local = json_decode(file_get_contents($this->jsonPath), true) ?? ['commandes' => []];
        $ids = array_column($local['commandes'], 'id');
        $commandeData['id'] = $ids ? max($ids) + 1 : 1;
        $local['commandes'][] = $commandeData;
        file_put_contents($this->jsonPath, json_encode($local, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE));
        return $commandeData;
    }
}