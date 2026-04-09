<?php
require_once __DIR__ . '/../service/CommandeService.php';
require_once __DIR__ . '/../service/MenuService.php';

/**
 * Controller pour la page "Commandes".
 * Gère l'affichage et la création de commandes.
 */
class CommandesController {

    private CommandeService $commandeService;
    private MenuService     $menuService;

    public function __construct() {
        $this->commandeService = new CommandeService();
        $this->menuService     = new MenuService();
    }

    /**
     * Affiche la liste des commandes + formulaire de nouvelle commande.
     */
    public function index(): void {

        $erreur = null;
        $succes = null;


        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $utilisateurId   = (int) ($_POST['utilisateurId']   ?? 0);
            $menuId          = (int) ($_POST['menuId']          ?? 0);
            $quantite        = (int) ($_POST['quantite']        ?? 1);
            $adresse         = trim($_POST['adresseLivraison']  ?? '');
            $dateLivraison   = trim($_POST['dateLivraison']     ?? '');

            if ($utilisateurId === 0 || $menuId === 0 || $adresse === '' || $dateLivraison === '') {
                $erreur = 'Tous les champs sont obligatoires.';
            } else {
                $this->commandeService->createCommande([
                    'utilisateurId'    => $utilisateurId,
                    'lignes'           => [['menuId' => $menuId, 'quantite' => $quantite]],
                    'dateCommande'     => date('Y-m-d'),
                    'adresseLivraison' => $adresse,
                    'dateLivraison'    => $dateLivraison,
                    'prixTotal'        => 0
                ]);
                $succes = 'Commande passée avec succès !';
            }
        }

        $commandes = $this->commandeService->getAllCommandes();
        $menus     = $this->menuService->getAllMenus();

        require_once __DIR__ . '/../view/commandes.php';
    }
}
