<?php
require_once __DIR__ . '/../service/MenuService.php';
require_once __DIR__ . '/../service/PlatsUtilisateursService.php';

/**
 * Controller pour la page "Menus".
 * Gère aussi la soumission du formulaire de création d'un menu (POST).
 */
class MenusController {

    private MenuService               $menuService;
    private PlatsUtilisateursService  $platsService;

    public function __construct() {
        $this->menuService  = new MenuService();
        $this->platsService = new PlatsUtilisateursService();
    }

    /**
     * Affiche la liste des menus.
     * Gère aussi la création via formulaire POST.
     */
    public function index(): void {

        $erreur  = null;
        $succes  = null;


        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $nom      = trim($_POST['nom']      ?? '');
            $createur = trim($_POST['createur'] ?? '');
            $platsIds = $_POST['platsIds']      ?? [];

            if ($nom === '' || $createur === '' || empty($platsIds)) {
                $erreur = 'Merci de remplir tous les champs et sélectionner au moins un plat.';
            } else {
                $this->menuService->createMenu([
                    'nom'          => $nom,
                    'createur'     => $createur,
                    'dateCreation' => date('Y-m-d'),
                    'platsIds'     => array_map('intval', $platsIds)
                ]);
                $succes = 'Menu créé avec succès !';
            }
        }

        $menus = $this->menuService->getAllMenus();
        $plats = $this->platsService->getAllPlats();

        require_once __DIR__ . '/../view/menus.php';
    }
}
