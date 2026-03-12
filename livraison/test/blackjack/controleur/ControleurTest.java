package blackjack.controleur;

import blackjack.modele.*;
import blackjack.vue.VueJeu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Tests ciblant quelques scénarios du contrôleur sans dépendances externes.
 */
public class ControleurTest {

    private Controleur controleur;
    private PartieBlackjackSpy partie;
    private TestVue vue;
    private Humain joueurA;
    private Humain joueurB;

    @Before
    public void setUp() {
        partie = new PartieBlackjackSpy();
        vue = new TestVue();
        joueurA = new Humain("Alice", 200);
        joueurB = new Humain("Bob", 150);
        controleur = new Controleur(partie, vue);
    }

    @Test
    public void demarrerJeu_sansJoueur() {
        controleur.demarrerJeu();

        assertEquals("Aucun joueur n'est présent !", vue.lastMessage);
        Assert.assertEquals(0, partie.getJoueurs().size());
    }

    @Test
    public void onMise_miseNulle() {
        double soldInitial = joueurA.getSold();

        controleur.onMise(joueurA, 0.0);

        assertEquals("Mise invalide pour Alice", vue.lastMessage);
        assertEquals(soldInitial, joueurA.getSold(), 0.0001);
        assertEquals(0, vue.modeleMisAJourCount);
    }

    @Test
    public void onMise_miseTropHaute() {
        double soldInitial = joueurA.getSold();

        controleur.onMise(joueurA, soldInitial + 50.0);

        assertEquals("Mise invalide pour Alice", vue.lastMessage);
        assertEquals(soldInitial, joueurA.getSold(), 0.0001);
        assertEquals(0, vue.modeleMisAJourCount);
    }

    @Test
    public void onMise_miseOK() {
        partie.getJoueurs().add(joueurA);

        controleur.onMise(joueurA, 50.0);

        assertEquals(150.0, joueurA.getSold(), 0.0001);
        assertEquals(50.0, joueurA.getMainJoueur().getMise(), 0.0001);
        assertSame(partie, vue.lastModeleMisAJour);
        assertEquals(1, vue.modeleMisAJourCount);
    }

    @Test
    public void onAction_pasLeTour() {
        partie.getJoueurs().add(joueurA);
        partie.getJoueurs().add(joueurB);
        partie.getEtat().setJoueurActuel(joueurA);

        controleur.onAction(joueurB, Action.TIRER);

        assertEquals("Ce n'est pas le tour de Bob", vue.lastMessage);
        assertEquals(0, partie.executerActionCalls);
    }

    /**
     * Espion minimaliste pour vérifier si executerAction est déclenché.
     */
    private static class PartieBlackjackSpy extends PartieBlackjack {
        int executerActionCalls = 0;

        @Override
        public void executerAction(Joueur joueur, Action action) {
            executerActionCalls++;
        }
    }

    /**
     * Vue factice qui enregistre les interactions utiles pour les assertions.
     */
    private static class TestVue implements VueJeu {
        String lastMessage;
        Object lastModeleMisAJour;
        int modeleMisAJourCount;
        EcouteurActionJeu dernierEcouteur;

        @Override
        public void afficherPartie(PartieBlackjack partie) {
            // non nécessaire pour ces tests
        }

        @Override
        public void afficherMessage(String message) {
            this.lastMessage = message;
        }

        @Override
        public Action demanderAction(Joueur joueur) {
            return Action.RESTER;
        }

        @Override
        public double demanderMise(Joueur joueur) {
            return 0;
        }

        @Override
        public void ajouterEcouteur(EcouteurActionJeu ecouteurActionJeu) {
            this.dernierEcouteur = ecouteurActionJeu;
        }

        @Override
        public void modeleMisAJour(Object partie) {
            this.lastModeleMisAJour = partie;
            this.modeleMisAJourCount++;
        }
    }

}
