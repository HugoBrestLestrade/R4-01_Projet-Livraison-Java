<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Commandes – MenuApp</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<nav class="navbar">
    <span class="navbar__logo">menuapp</span>
    <ul class="navbar__links">
        <li><a href="index.php?page=plats">Les Plats</a></li>
        <li><a href="index.php?page=menus">Les Menus</a></li>
        <li><a href="index.php?page=commandes" class="active">Commandes</a></li>
    </ul>
</nav>

<main> <section class="hero">
        <img class="hero__img"
             src="assets/images/commandes-hero.jpg"
             alt="Vos commandes">
        <div class="hero__overlay"></div>
        <div class="hero__content">
            <h1 class="hero__titre">Vos commandes</h1>
            <p class="hero__sous-titre">Passez une commande et faites-vous livrer à domicile</p>
            <a href="#commander" class="hero__btn">Commander maintenant →</a>
        </div>
    </section>

    <div class="container">
        <div class="form-section" id="commander">
            <h2>Passer une commande</h2>

            <?php if (!empty($succes)): ?>
                <div class="alert alert--succes"><?= htmlspecialchars($succes) ?></div>
            <?php endif; ?>

            <?php if (!empty($erreur)): ?>
                <div class="alert alert--erreur"><?= htmlspecialchars($erreur) ?></div>
            <?php endif; ?>

            <form action="index.php?page=commandes" method="POST">
                <div class="form-group">
                    <label for="utilisateurId">Votre identifiant abonné</label>
                    <input type="number" id="utilisateurId" name="utilisateurId" placeholder="ex: 1" min="1" required>
                </div>

                <div class="form-group">
                    <label for="menuId">Menu souhaité</label>
                    <select id="menuId" name="menuId" required>
                        <option value="">-- Choisir un menu --</option>
                        <?php foreach ($menus as $menu): ?>
                            <option value="<?= $menu->id ?>">
                                <?= htmlspecialchars($menu->nom) ?> (par <?= htmlspecialchars($menu->createur) ?>)
                            </option>
                        <?php endforeach; ?>
                    </select>
                </div>

                <div class="form-group">
                    <label for="quantite">Quantité</label>
                    <input type="number" id="quantite" name="quantite" value="1" min="1" max="20" required>
                </div>

                <div class="form-group">
                    <label for="adresseLivraison">Adresse de livraison</label>
                    <input type="text" id="adresseLivraison" name="adresseLivraison" placeholder="ex: 12 rue des Oliviers, Aix-en-Provence" required>
                </div>

                <div class="form-group">
                    <label for="dateLivraison">Date de livraison souhaitée</label>
                    <input type="date" id="dateLivraison" name="dateLivraison" min="<?= date('Y-m-d') ?>" required>
                </div>

                <button type="submit" class="btn">Commander →</button>
            </form>
        </div>

        <h2 class="section-title">Historique des commandes</h2>

        <?php if (empty($commandes)): ?>
            <p style="text-align:center; color: var(--texte-gris); font-family: Arial, sans-serif;">
                Aucune commande enregistrée.
            </p>
        <?php else: ?>
            <div class="grid">
                <?php foreach ($commandes as $commande): ?>
                    <div class="card">
                        <span class="card__tag">Commande #<?= $commande->id ?></span>
                        <h2 class="card__nom">Abonné #<?= $commande->utilisateurId ?></h2>
                        <p class="card__meta">Passée le <?= htmlspecialchars($commande->dateCommande) ?></p>
                        <p class="card__description">
                            <?php foreach ($commande->lignes as $ligne): ?>
                                Menu #<?= $ligne['menuId'] ?> × <?= $ligne['quantite'] ?><br>
                            <?php endforeach; ?>
                        </p>
                        <p class="card__meta">
                            📍 <?= htmlspecialchars($commande->adresseLivraison) ?><br>
                            🗓 Livraison : <?= htmlspecialchars($commande->dateLivraison) ?>
                        </p>
                        <?php if ($commande->prixTotal > 0): ?>
                            <p class="card__prix" style="margin-top: 0.75rem;">
                                <?= number_format($commande->prixTotal, 2) ?> €
                            </p>
                        <?php endif; ?>
                    </div>
                <?php endforeach; ?>
            </div>
        <?php endif; ?>
    </div>

</main> <footer>
    &copy; <?= date('Y') ?> MenuApp — Projet Microservices IUT Aix-Marseille
</footer>

</body>
</html>