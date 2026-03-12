package cartes.vue;

import cartes.Carte;
import cartes.Couleur;
import cartes.Valeur;

public class VueCarteConsole implements VueCarte {
    
    @Override
    public void afficher(Carte carte) {
        if (carte == null) {
            System.out.print("[NULL]");
            return;
        }
        
        String symboleValeur = getSymboleValeur(carte.getValeur());
        String symboleCouleur = getSymboleCouleur(carte.getCouleur());
        
        System.out.print("[" + symboleValeur + symboleCouleur + "]");
    }
  
    @Override
    public void afficherCachee() {
        System.out.print("[X]");
    }
    
    private String getSymboleValeur(Valeur valeur) {
        return switch(valeur) {
            case AS -> "A";
            case VALET -> "J";
            case DAME -> "Q";
            case ROI -> "K";
            default -> String.valueOf(valeur.getValeurNumerique());
        };
    }
    
    private String getSymboleCouleur(Couleur couleur) {
        return switch(couleur) {
            case COEUR -> "♥";
            case CARREAU -> "♦"; 
            case TREFLE -> "♣";
            case PIQUE -> "♠";
        };
    }
}