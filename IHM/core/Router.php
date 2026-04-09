<?php
/**
 * Router : lit le paramètre "page" dans l'URL et instancie le bon controller.
 * Exemple : index.php?page=plats  →  PlatsController
 */
class Router {

    public function dispatch(): void {

        $page = $_GET['page'] ?? 'plats';

        switch ($page) {
            case 'plats':
                require_once __DIR__ . '/../app/controller/PlatsController.php';
                $controller = new PlatsController();
                $controller->index();
                break;

            case 'menus':
                require_once __DIR__ . '/../app/controller/MenusController.php';
                $controller = new MenusController();
                $controller->index();
                break;

            case 'commandes':
                require_once __DIR__ . '/../app/controller/CommandesController.php';
                $controller = new CommandesController();
                $controller->index();
                break;

            default:

                header('Location: index.php?page=plats');
                exit;
        }
    }
}
