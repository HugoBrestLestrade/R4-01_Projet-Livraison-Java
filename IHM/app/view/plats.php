<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Les Plats – MenuApp</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body> <nav class="navbar">
    <span class="navbar__logo">menuapp</span>
    <ul class="navbar__links">
        <li><a href="index.php?page=plats" class="active">Les Plats</a></li>
        <li><a href="index.php?page=menus">Les Menus</a></li>
        <li><a href="index.php?page=commandes">Commandes</a></li>
    </ul>
</nav>

<main> <section class="hero">
        <img class="hero__img"
             src="assets/images/salade.avif"
             alt="Plats frais">
        <div class="hero__overlay"></div>
        <div class="hero__content">
            <h1 class="hero__titre">Nos plats du moment</h1>
            <p class="hero__sous-titre">Frais, locaux, délicieux</p>
            <a href="#plats" class="hero__btn">Découvrir →</a>
        </div>
    </section>

    <div class="container" id="plats">
        <?php if (empty($plats)): ?>
            <p style="text-align:center; color: var(--texte-gris); font-family: Arial, sans-serif;">
                Aucun plat disponible pour le moment.
            </p>
        <?php else: ?>
            <div class="grid">
                <?php foreach ($plats as $plat): ?>
                    <div class="card">
                        <span class="card__tag">Plat</span>
                        <h2 class="card__nom"><?= htmlspecialchars($plat->nom) ?></h2>
                        <p class="card__description"><?= htmlspecialchars($plat->description) ?></p>
                        <p class="card__prix"><?= number_format($plat->prix, 2) ?> €</p>
                    </div>
                <?php endforeach; ?>
            </div>
        <?php endif; ?>
    </div>

</main> <footer>
    &copy; <?= date('Y') ?> MenuApp — Projet Microservices IUT Aix-Marseille
</footer>

<script>
    const plats = document.getElementById('plats');
    const observer = new IntersectionObserver(([entry]) => {
        if (entry.isIntersecting) {
            plats.classList.add('visible');
        }
    }, { threshold: 0.1 });
    observer.observe(plats);
</script>

</body>
</html>