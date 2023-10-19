import java.lang.reflect.GenericDeclaration;
import java.util.Scanner;

public class Game {
    private int plateau[];
    private int posPlayer;

    public Game() {
        plateau = new int[64];
        posPlayer = 0;
        initPlateau();
    }

    public int jetDados() {
        int dado = 1 + (int) (Math.random() * ((6 - 1) + 1));
        return dado;
    }

    /**
     * Permet d'initialiser un tableau rempli de 64 int, de 0 à 63.
     */
    public void initPlateau() {
        for (int i = 0; i < plateau.length; i++) {
            plateau[i] = i;
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

    public void movePlayer() throws PersonnageHorsPlateauException {
        while (posPlayer < 63) {
            int resultat = jetDados();
            posPlayer += resultat;
            if (posPlayer > 63) {
                throw new PersonnageHorsPlateauException();
            }
            if (posPlayer == 63) {
                System.out.println("OMG t'as fini");
            }
            System.out.println("Vous avez fait " + resultat + " et avancé à sur la case " + posPlayer);
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