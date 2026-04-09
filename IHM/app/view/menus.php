<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Les Menus – MenuApp</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<nav class="navbar">
    <span class="navbar__logo">menuapp</span>
    <ul class="navbar__links">
        <li><a href="index.php?page=plats">Les Plats</a></li>
        <li><a href="index.php?page=menus" class="active">Les Menus</a></li>
        <li><a href="index.php?page=commandes">Commandes</a></li>
    </ul>
</nav>

<main> <section class="hero">
        <img class="hero__img"
             src="assets/images/menus-hero.jpg"
             alt="Composer vos menus">
        <div class="hero__overlay"></div>
        <div class="hero__content">
            <h1 class="hero__titre">Composer & découvrir</h1>
            <p class="hero__sous-titre">Créez votre menu personnalisé ou choisissez parmi nos propositions</p>
            <a href="#formulaire" class="hero__btn">Créer un menu →</a>
        </div>
    </section>

    <div class="container">
        <div class="form-section" id="formulaire">
            <h2>Créer un nouveau menu</h2>

            <?php if (!empty($succes)): ?>
                <div class="alert alert--succes"><?= htmlspecialchars($succes) ?></div>
            <?php endif; ?>

            <?php if (!empty($erreur)): ?>
                <div class="alert alert--erreur"><?= htmlspecialchars($erreur) ?></div>
            <?php endif; ?>

            <form action="index.php?page=menus" method="POST">
                <div class="form-group">
                    <label for="nom">Nom du menu</label>
                    <input type="text" id="nom" name="nom" placeholder="ex: Menu Méditerranéen" required>
                </div>

                <div class="form-group">
                    <label for="createur">Votre nom</label>
                    <input type="text" id="createur" name="createur" placeholder="ex: Jean Dupont" required>
                </div>

                <div class="form-group">
                    <label>Sélectionner les plats</label>
                    <div class="checkboxes">
                        <?php foreach ($plats as $plat): ?>
                            <label class="checkbox-item">
                                <input type="checkbox" name="platsIds[]" value="<?= $plat->id ?>">
                                <?= htmlspecialchars($plat->nom) ?> — <?= number_format($plat->prix, 2) ?> €
                            </label>
                        <?php endforeach; ?>
                    </div>
                </div>
                <button type="submit" class="btn">Créer le menu →</button>
            </form>
        </div>

        <h2 class="section-title">Tous les menus</h2>

        <?php if (empty($menus)): ?>
            <p style="text-align:center; color: var(--texte-gris); font-family: Arial, sans-serif;">
                Aucun menu disponible.
            </p>
        <?php else: ?>
            <div class="grid">
                <?php foreach ($menus as $menu): ?>
                    <div class="card">
                        <span class="card__tag">Menu</span>
                        <h2 class="card__nom"><?= htmlspecialchars($menu->nom) ?></h2>
                        <p class="card__meta">
                            Par <?= htmlspecialchars($menu->createur) ?> · <?= htmlspecialchars($menu->dateCreation) ?>
                        </p>
                        <p class="card__description" style="margin-top: 0.5rem;">
                            <?= count($menu->platsIds) ?> plat(s) inclus
                        </p>
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