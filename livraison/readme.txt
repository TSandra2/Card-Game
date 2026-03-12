Jeu Blackjack

Un jeu de Blackjack developpe en Java avec interface console et graphique.

Commandes:

- ant compile       : Compiler le projet
- ant jar           : Creer le fichier JAR
- ant doc           : Generer la documentation
- ant runConsole    : Lancer la version console
- ant runGUI        : Lancer la version graphique
- ant clean         : Nettoyer les fichiers generes


Structure:

src/
├── observer/     # Pattern Observer
├── cartes/       # Systeme de cartes
└── blackJack/    # Logique du jeu
    ├── modele/
    ├── vue/
    └── controleur/

Lancement rapide:

1. ant compile
2. ant runConsole   (pour la version console)
3. ant runGUI       (pour la version graphique)

Dependances:
- Java 8
- Apache Ant

Developpe dans le cadre du module de méthode de conception