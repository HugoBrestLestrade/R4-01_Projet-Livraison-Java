<?php
require_once __DIR__ . '/../service/PlatsUtilisateursService.php';

/**
 * Controller pour la page "Plats".
 * Récupère les données via le service et les passe à la vue.
 */
class PlatsController {

    private PlatsUtilisateursService $service;

    public function __construct() {
        $this->service = new PlatsUtilisateursService();
    }

    /**
     * Affiche la liste de tous les plats.
     */
    public function index(): void {

        $plats = $this->service->getAllPlats();


        require_once __DIR__ . '/../view/plats.php';
    }
}
