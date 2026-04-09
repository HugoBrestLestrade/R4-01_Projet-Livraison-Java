<?php
require_once __DIR__ . '/../service/CommandeService.php';
require_once __DIR__ . '/../service/MenuService.php';
require_once __DIR__ . '/../service/PlatsUtilisateursService.php';

class CommandesController {

    private CommandeService          $commandeService;
    private MenuService              $menuService;
    private PlatsUtilisateursService $platsService;

    public function __construct() {
        $this->commandeService = new CommandeService();
        $this->menuService     = new MenuService();
        $this->platsService    = new PlatsUtilisateursService();
    }

    public function index(): void {

        $erreur = null;
        $succes = null;

        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $utilisateurId = (int) ($_POST['utilisateurId']  ?? 0);
            $menuId        = (int) ($_POST['menuId']         ?? 0);
            $quantite      = (int) ($_POST['quantite']       ?? 1);
            $adresse       = trim($_POST['adresseLivraison'] ?? '');
            $dateLivraison = trim($_POST['dateLivraison']    ?? '');

            if ($utilisateurId === 0 || $menuId === 0 || $adresse === '' || $dateLivraison === '') {
                $erreur = 'Tous les champs sont obligatoires.';
            } else {
                // Calcul du prix total
                $prixTotal = 0.0;
                $menu = $this->menuService->getMenuById($menuId);
                if ($menu) {
                    foreach ($menu->platsIds as $platId) {
                        $plat = $this->platsService->getPlatById($platId);
                        if ($plat) $prixTotal += $plat->prix;
                    }
                    $prixTotal *= $quantite;
                }

                $this->commandeService->createCommande([
                    'utilisateurId'    => $utilisateurId,
                    'lignes'           => [['menuId' => $menuId, 'quantite' => $quantite]],
                    'dateCommande'     => date('Y-m-d'),
                    'adresseLivraison' => $adresse,
                    'dateLivraison'    => $dateLivraison,
                    'prixTotal'        => $prixTotal
                ]);
                $succes = 'Commande passée avec succès !';
            }
        }

        $commandes = $this->commandeService->getAllCommandes();
        $menus     = $this->menuService->getAllMenus();

        require_once __DIR__ . '/../view/commandes.php';
    }
}