package main;

import blackjack.controleur.Controleur;
import blackjack.modele.*;
import blackjack.vue.VueGUI;

import java.awt.*;


public class Main {
    public static void main(String[] args) {

        PartieBlackjack blackjack = new PartieBlackjack();

        blackjack.ajouterJoueur(new IA("IA GPT", 100, new StrategieBasic()));

        blackjack.ajouterJoueur(new Humain("Tom", 100));
        blackjack.ajouterJoueur(new Humain("Alex", 100));
       // blackjack.ajouterJoueur(new Humain("Steve", 100));


        blackjack.ajouterJoueur(new IA("IA DeepSeek", 100, new StrategieBasic()));

        //blackjack.ajouterJoueur(new Humain("Ben", 100));
        VueGUI view = new VueGUI();
        view.setCardWidth(150);
        view.setCardHeight(200);

        view.setPreferredSize(new Dimension(1400, 800));

        Controleur controleur = new Controleur(blackjack, view);
        controleur.demarrerJeu();
    }
}