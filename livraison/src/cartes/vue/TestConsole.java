package cartes.vue;

import cartes.*;

public class TestConsole {
    public static void main(String[] args) {
        System.out.println("🎴 TEST LIBRAIRIE CARTES 🎴");
        
        // Créer des cartes
        Carte asCoeur = new CarteStandard(Couleur.COEUR, Valeur.AS);
        Carte roiPique = new CarteStandard(Couleur.PIQUE, Valeur.ROI);
        Carte dameCarreau = new CarteStandard(Couleur.CARREAU, Valeur.DAME);
        Carte dixTrefle = new CarteStandard(Couleur.TREFLE, Valeur.DIX);
        
        VueCarteConsole vueCarte = new VueCarteConsole();
        VuePaquetConsole vuePaquet = new VuePaquetConsole(vueCarte);
        
        // Test vue carte simple
        System.out.println("=== VUE CARTE ===");
        vueCarte.afficherMultiple(asCoeur, roiPique, dameCarreau, dixTrefle);
        
        // Test carte cachée
        asCoeur.setVisible(false);
        System.out.print("Avec carte cachée: ");
        vueCarte.afficherMultiple(asCoeur, roiPique);
        asCoeur.setVisible(true);
        
        // Test paquet
        System.out.println("\n=== VUE PAQUET ===");
        Paquet paquet = new PaquetStandard();
        paquet.ajouterCarte(asCoeur);
        paquet.ajouterCarte(roiPique);
        paquet.ajouterCarte(dameCarreau);
        paquet.ajouterCarte(dixTrefle);
        
        System.out.print("Paquet complet: ");
        vuePaquet.afficher(paquet);
        
        // Test éventail
        System.out.print("Éventail (2 cartes): ");
        vuePaquet.afficherEventail(paquet, 2);
        
        // Test mélange
        System.out.println("\n=== TEST MÉLANGE ===");
        System.out.print("Avant mélange: ");
        vuePaquet.afficher(paquet);
        paquet.melangerCartes();
        System.out.print("Après mélange: ");
        vuePaquet.afficher(paquet);
        
        // Test tirage
        System.out.println("\n=== TEST TIRAGE ===");
        System.out.println("Cartes dans paquet: " + paquet.getNombreCartes());
        Carte carteTiree = paquet.piocherCarte();
        System.out.print("Carte tirée: ");
        vueCarte.afficher(carteTiree);
        System.out.println("\nCartes restantes: " + paquet.getNombreCartes());
        System.out.print("Paquet restant: ");
        vuePaquet.afficher(paquet);
        
        // Test factory
        System.out.println("\n=== TEST FACTORY ===");
        FabriqueJeu factory = new FabriqueJeuStandard();
        Paquet nouveauPaquet = factory.creerPaquet();
        
        // Créer un jeu complet
        for (Couleur couleur : Couleur.values()) {
            for (Valeur valeur : Valeur.values()) {
                nouveauPaquet.ajouterCarte(factory.creerCarte(valeur, couleur));
            }
        }
        
        System.out.println("Paquet créé avec factory: " + nouveauPaquet.getNombreCartes() + " cartes");
        nouveauPaquet.melangerCartes();
        System.out.print("5 premières cartes: ");
        for (int i = 0; i < 5; i++) {
            vueCarte.afficher(nouveauPaquet.piocherCarte());
            System.out.print(" ");
        }
    }
}