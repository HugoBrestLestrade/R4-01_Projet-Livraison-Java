<?php
require_once __DIR__ . '/../model/Menu.php';

class MenuService {

    private string $jsonPath;

    public function __construct() {
        $this->jsonPath = __DIR__ . '/../../json/menus.json';
    }

    public function getAllMenus(): array {
        if (!file_exists($this->jsonPath)) return [];
        $local = json_decode(file_get_contents($this->jsonPath), true) ?? [];
        return array_map(fn($item) => Menu::fromArray($item), $local['menus'] ?? []);
    }

    public function getMenuById(int $id): ?Menu {
        foreach ($this->getAllMenus() as $menu) {
            if ($menu->id === $id) return $menu;
        }
        return null;
    }

    public function createMenu(array $menuData): array {
        $local = json_decode(file_get_contents($this->jsonPath), true) ?? ['menus' => []];
        $ids = array_column($local['menus'], 'id');
        $menuData['id'] = $ids ? max($ids) + 1 : 1;
        $local['menus'][] = $menuData;
        file_put_contents($this->jsonPath, json_encode($local, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE));
        return $menuData;
    }
}