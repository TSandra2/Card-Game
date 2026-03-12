package cartes.vue;

import cartes.Carte;

public interface VueCarte {
    
    void afficher(Carte carte);
    
    void afficherCachee();
    
    default void afficherAvecVisibilite(Carte carte) {
        if (carte == null) {
            throw new IllegalArgumentException("La carte ne peut pas être null");
        }
        
        if (carte.estVisible()) {
            afficher(carte);
        } else {
            afficherCachee();
        }
    }
    
    default void afficherMultiple(Carte... cartes) {
        if (cartes == null) {
            throw new IllegalArgumentException("Les cartes ne peuvent pas être null");
        }
        
        for (Carte carte : cartes) {
            afficherAvecVisibilite(carte);
            System.out.print(" ");
        }
        System.out.println();
    }
}