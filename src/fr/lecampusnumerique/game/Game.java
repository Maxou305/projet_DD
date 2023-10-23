package fr.lecampusnumerique.game;

import fr.lecampusnumerique.exceptions.PersonnageHorsPlateauException;
import fr.lecampusnumerique.game.ennemis.Dragon;
import fr.lecampusnumerique.game.ennemis.Gobelin;
import fr.lecampusnumerique.game.ennemis.Sorcier;
import fr.lecampusnumerique.game.potions.PopoBig;
import fr.lecampusnumerique.game.potions.PopoMini;
import fr.lecampusnumerique.offense.guerrier.Epee;
import fr.lecampusnumerique.offense.guerrier.Massue;
import fr.lecampusnumerique.offense.magicien.BouleDeFeu;
import fr.lecampusnumerique.offense.magicien.Eclair;
import fr.lecampusnumerique.personnages.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final ArrayList<Cell> plateau;
    private int posPlayer;
    private final Personnage player;
    Scanner eventUser = new Scanner(System.in);


    /**
     * Constructeur de la classe Game
     */
    public Game(Personnage pPlayer) {
        plateau = new ArrayList<>();
        posPlayer = 0;
        initRandomCasesPlateau();
        player = pPlayer;
    }

    /**
     * Méthode permettant de gérer le lancer de dé. Construit grâce à un random compris en tre 1 et 6.
     *
     * @return valeur du jet de dés
     */
    public int jetDados() {
        return 1 + (int) (Math.random() * ((6 - 1) + 1));
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

    public void initRandomCasesPlateau() {
        int nbrDragons = 4;
        int nbrSorciers = 10;
        int nbrGobelins = 10;
        int nbrMassues = 5;
        int nbrEpees = 4;
        int nbrEclairs = 5;
        int nbrBoulesDeFeu = 2;
        int nbrPopoMini = 6;
        int nbrPopoBig = 2;
        int nbrCellVide = 16;

        int cases = 0;

        while (cases < 64) {
            int choix = 1 + (int) (Math.random() * ((10 - 1) + 1));
            if (choix == 1 && nbrDragons > 0) {
                plateau.add(new Dragon());
                nbrDragons--;
                cases++;
            }
            if (choix == 2 && nbrSorciers > 0) {
                plateau.add(new Sorcier());
                nbrSorciers--;
                cases++;
            }
            if (choix == 3 && nbrGobelins > 0) {
                plateau.add(new Gobelin());
                nbrGobelins--;
                cases++;
            }
            if (choix == 4 && nbrMassues > 0) {
                plateau.add(new Massue());
                nbrMassues--;
                cases++;
            }
            if (choix == 5 && nbrEpees > 0) {
                plateau.add(new Epee());
                nbrEpees--;
                cases++;
            }
            if (choix == 6 && nbrEclairs > 0) {
                plateau.add(new Eclair());
                nbrEclairs--;
                cases++;
            }
            if (choix == 7 && nbrBoulesDeFeu > 0) {
                plateau.add(new BouleDeFeu());
                nbrBoulesDeFeu--;
                cases++;
            }
            if (choix == 8 && nbrPopoMini > 0) {
                plateau.add(new PopoMini());
                nbrPopoMini--;
                cases++;
            }
            if (choix == 9 && nbrPopoBig > 0) {
                plateau.add(new PopoBig());
                nbrPopoBig--;
                cases++;
            }
            if (choix == 10 && nbrCellVide > 0) {
                plateau.add(new CellVide());
                nbrCellVide--;
                cases++;
            }
        }
    }

    // ----- GESTION PLAYER ------------------------------------------------------------------------------------

//    public void movePlayer() {
//        while (posPlayer < 63) {
//            int result = jetDados();
//            posPlayer += result;
//            System.out.println("Vous avez fait " + result + " et avancé à sur la case " + posPlayer);
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
//                int result = jetDados();
//                posPlayer = plateau[posPlayer + result];
//                System.out.println("Vous avez fait " + result + " et avancé à sur la case " + posPlayer);
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
            int result = jetDados();
            posPlayer += result;
            if (posPlayer > 63) {
                throw new PersonnageHorsPlateauException();
            }
            if (posPlayer == 63) {
                System.out.println("OMG t'as fini");
            }
            System.out.println("Vous avez fait " + result + " et avancé sur la case " + posPlayer);
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