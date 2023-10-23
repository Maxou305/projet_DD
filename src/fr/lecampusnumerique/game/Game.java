package fr.lecampusnumerique.game;

import fr.lecampusnumerique.exceptions.PersonnageHorsPlateauException;
import fr.lecampusnumerique.game.ennemis.Dragon;
import fr.lecampusnumerique.game.ennemis.Gobelin;
import fr.lecampusnumerique.game.ennemis.Sorcier;
import fr.lecampusnumerique.game.potions.PopoBig;
import fr.lecampusnumerique.game.potions.PopoMini;
import fr.lecampusnumerique.offense.guerrier.Massue;
import fr.lecampusnumerique.offense.magicien.BouleDeFeu;
import fr.lecampusnumerique.offense.magicien.Eclair;
import fr.lecampusnumerique.personnages.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Cell> plateau;
    private int posPlayer;
    private Personnage player;
    Scanner eventUser = new Scanner(System.in);


    /**
     * Constructeur de la classe Game
     */
    public Game(Personnage pPlayer) {
        plateau = new ArrayList<>();
        posPlayer = 0;
        initCasesPlateau();
        player = pPlayer;
    }

    /**
     * Méthode permettant de gérer le lancer de dé. Construit grâce à un random compris en tre 1 et 6.
     *
     * @return valeur du jet de dés
     */
    public int jetDados() {
        int dado = 1 + (int) (Math.random() * ((6 - 1) + 1));
        return dado;
    }

    /**
     * Permet d'initialiser un tableau avec les ArrayList. Dans ce cas, il est nécessaire de remplir D'ABORD l'ArrayList de cellules vides
     */
    public void initCasesPlateau() {
        for (int i = 0; i < 64; i++) {
            plateau.add(new CellVide());
        }
        for (int i = 0; i < 64; i++) {
            if ((i == 45) || (i == 52) || (i == 56) || (i == 62)) {
                plateau.set(i, new Dragon());
            } else if ((i == 10) || (i == 20) || (i == 25) || (i == 32) || (i == 35) || (i == 36) || (i == 37) || (i == 40) || (i == 44) || (i == 47)) {
                plateau.set(i, new Sorcier());
            } else if ((i == 3) || (i == 6) || (i == 9) || (i == 12) || (i == 15) || (i == 18) || (i == 21) || (i == 24) || (i == 27) || (i == 30)) {
                plateau.set(i, new Gobelin());
            } else if ((i == 2) || (i == 11) || (i == 5) || (i == 22) || (i == 38)) {
                plateau.set(i, new Massue());
            } else if ((i == 19) || (i == 26) || (i == 42) || (i == 53)) {
                plateau.set(i, new Eclair());
            } else if ((i == 48) || (i == 49)) {
                plateau.set(i, new BouleDeFeu());
            } else if ((i == 7) || (i == 13) || (i == 31) || (i == 33) || (i == 39) || (i == 43)) {
                plateau.set(i, new PopoMini());
            } else if ((i == 28) || (i == 41)) {
                plateau.set(i, new PopoBig());
            }
        }
    }


    // ----- GESTION PLAYER ------------------------------------------------------------------------------------

//    public void movePlayer() {
//        while (posPlayer < 63) {
//            int resultat = jetDados();
//            posPlayer += resultat;
//            System.out.println("Vous avez fait " + resultat + " et avancé à sur la case " + posPlayer);
//            if (posPlayer > 63) {
//                posPlayer = 126 - posPlayer;
//            }
//            if (posPlayer == 63) {
//                System.out.println("OMG t'as fini");
//            }
//
//        }
//        System.out.println("Normalement tout va bien (je crois)");
//    }
//    public void movePlayer() {
//        try {
//            while (posPlayer < 63) {
//                int resultat = jetDados();
//                posPlayer = plateau[posPlayer + resultat];
//                System.out.println("Vous avez fait " + resultat + " et avancé à sur la case " + posPlayer);
//            }
//        } catch(ArrayIndexOutOfBoundsException e){
//            System.out.println("STOOOOOOOOOOOOOOP TU VAS TROP LOIN !!!!!!!!");
//        }
//    }

    /**
     * Méthode permettant de faire avancer le joueur, à chaque itération la variable posPlayer est mise à jour.
     *
     * @throws PersonnageHorsPlateauException permet de relever une exception et de la traiter lorsque le joueur sort du plateau
     */
    public void movePlayer() throws PersonnageHorsPlateauException {
        while (posPlayer < 64) {
            int resultat = jetDados();
            posPlayer += resultat;
            if (posPlayer > 63) {
                throw new PersonnageHorsPlateauException();
            }
            if (posPlayer == 63) {
                System.out.println("OMG t'as fini");
            }
            System.out.println("Vous avez fait " + resultat + " et avancé sur la case " + posPlayer);
            plateau.get(posPlayer).interaction(player);
            String temp = eventUser.nextLine();
        }
        System.out.println("Normalement tout va bien (je crois)");
    }

    // -------------------------------------- GESTION DE LA PARTIE --------------------------------------

    /**
     * Méthode permettant de jouer au jeu
     */
    public void playGame() {
        // test de la méthode et renvoie d'erreur si besoin
        try {
            movePlayer();
        } catch (PersonnageHorsPlateauException e) {
            System.out.println("STOOOOOOOOOOOOOOP TU VAS TROP LOIN !!!!!!!!");
        }
    }
}