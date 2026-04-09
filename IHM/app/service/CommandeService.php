<?php
require_once __DIR__ . '/ApiClient.php';
require_once __DIR__ . '/../model/Commande.php';

/**
 * Service pour le composant "Commandes" (port 3005).
 */
class CommandeService {

    private string $baseUrl = 'http://localhost:3005';

    /**
     * Retourne toutes les commandes.
     *
     * @return Commande[]
     */
    public function getAllCommandes(): array {
        $data = ApiClient::get($this->baseUrl . '/commandes');
        return array_map(fn($item) => Commande::fromArray($item), $data);
    }

    /**
     * Crée une nouvelle commande.
     *
     * @param array $commandeData
     * @return array Réponse de l'API
     */
    public function createCommande(array $commandeData): array {
        return ApiClient::post($this->baseUrl . '/commandes', $commandeData);
    }
}
