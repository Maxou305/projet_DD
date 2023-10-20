package fr.lecampusnumerique.game;

import fr.lecampusnumerique.exceptions.PersonnageHorsPlateauException;
import fr.lecampusnumerique.game.ennemis.Dragon;
import fr.lecampusnumerique.game.potions.PopoBig;
import fr.lecampusnumerique.offense.guerrier.Epee;

import java.util.ArrayList;

public class Game {
    private ArrayList<Cell> plateau;
    private int posPlayer;

    public Game() {
        plateau = new ArrayList<Cell>();
        posPlayer = 0;
        initPlateau();
    }

    public int jetDados() {
        int dado = 1 + (int) (Math.random() * ((6 - 1) + 1));
        return dado;
    }

    /**
     * Permet d'initialiser un tableau avec les ArrayList
     */
    public void initPlateau() {
        plateau.add(new CellVide());
        plateau.add(new Dragon());
        plateau.add(new Epee());
        plateau.add(new PopoBig());
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

    public void movePlayer() throws PersonnageHorsPlateauException {
        while (posPlayer < 4) {
//            int resultat = jetDados();
            posPlayer += 1;
            if (posPlayer > 63) {
                throw new PersonnageHorsPlateauException();
            }
            if (posPlayer == 63) {
                System.out.println("OMG t'as fini");
            }
            System.out.println("Vous avez fait " + 1 + " et avancé à sur la case " + posPlayer);
            plateau.get(posPlayer-1).interact();
        }
        System.out.println("Normalement tout va bien (je crois)");
    }

    // -------------------------------------- GESTION DE LA PARTIE --------------------------------------

    public void playGame() {
        // test de la méthode et renvoie d'erreur si besoin
        try {
            movePlayer();
        } catch (PersonnageHorsPlateauException e) {
            System.out.println("STOOOOOOOOOOOOOOP TU VAS TROP LOIN !!!!!!!!");
        }
    }
}