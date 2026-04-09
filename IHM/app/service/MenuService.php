<?php
require_once __DIR__ . '/ApiClient.php';
require_once __DIR__ . '/../model/Menu.php';

/**
 * Service pour le composant "Menus" (port 3004).
 */
class MenuService {

    private string $baseUrl = 'http://localhost:3004';

    /**
     * Retourne tous les menus disponibles.
     *
     * @return Menu[]
     */
    public function getAllMenus(): array {
        $data = ApiClient::get($this->baseUrl . '/menus');
        return array_map(fn($item) => Menu::fromArray($item), $data);
    }

    /**
     * Retourne un menu par son id.
     *
     * @param int $id
     * @return Menu|null
     */
    public function getMenuById(int $id): ?Menu {
        $data = ApiClient::get($this->baseUrl . '/menus/' . $id);
        return empty($data) ? null : Menu::fromArray($data);
    }

    /**
     * Crée un nouveau menu via POST.
     *
     * @param array $menuData  Données brutes du menu
     * @return array Réponse de l'API
     */
    public function createMenu(array $menuData): array {
        return ApiClient::post($this->baseUrl . '/menus', $menuData);
    }
}