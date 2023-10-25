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
    private boolean winGame = false;

    /**
     * Constructeur de la classe Game.
     */
    public Game(Personnage pPlayer) {
        plateau = new ArrayList<>();
        pPlayer.setPosPlayer(0);
//        initCasesPlateau();
        initRandomCasesPlateau();
    }

    /**
     * Méthode permettant de gérer le lancer de dé. Construit grâce à un random compris en tre 1 et 6.
     *
     * @return valeur du jet de dés.
     */
    public int jetDados() {
        return 1 + (int) (Math.random() * ((6 - 1) + 1));
    }

    /**
     * Permet d'initialiser le plateau de jeu.
     */
    public void initCasesPlateau() {
        for (int i = 0; i < 64; i++) {
            switch (i) {
                case 45, 52, 56, 62 -> plateau.add(new Dragon());
                case 10, 20, 25, 32, 35, 36, 37, 40, 44, 47 -> plateau.add(new Sorcier());
                case 3, 6, 9, 12, 15, 18, 21, 24, 27, 30 -> plateau.add(new Gobelin());
                case 2, 11, 5, 22, 38 -> plateau.add(new Massue());
                case 19, 26, 42, 53 -> plateau.add(new Eclair());
                case 48, 49 -> plateau.add(new BouleDeFeu());
                case 7, 13, 31, 33, 39, 43 -> plateau.add(new PopoMini());
                case 28, 41 -> plateau.add(new PopoBig());
                default -> plateau.add(new CellVide());
            }
        }
    }

    /**
     * Permet d'initialiser le plateau de jeu avec des cases placées aléatoirement.
     */
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

    /**
     * Méthode permettant de faire avancer le joueur, à chaque itération la variable posPlayer est mise à jour.
     *
     * @throws PersonnageHorsPlateauException permet de relever une exception et de la traiter lorsque le joueur sort du plateau
     */
//    public void movePlayer(Personnage pPlayer) throws PersonnageHorsPlateauException {
//        int result = jetDados();
//        pPlayer.setPosPlayer(pPlayer.getPosPlayer() + result);
//        System.out.println("Vous avez fait " + result + " et avancé sur la case " + pPlayer.getPosPlayer());
//        if (pPlayer.getPosPlayer() > 63) {
//            throw new PersonnageHorsPlateauException();
//        }
//    }

    /**
     * Permet de faire avancer le joueur en fonction d'un jet de dés.
     *
     * @param pPlayer joueur devant se déplacer
     */
    public void movePlayer(Personnage pPlayer) {
        int result = jetDados();
        pPlayer.setPosPlayer(pPlayer.getPosPlayer() + result);
        System.out.println("Vous avez fait " + result + " et avancé sur la case " + pPlayer.getPosPlayer());
        if (pPlayer.getPosPlayer() > 63) {
            pPlayer.setPosPlayer(126 - pPlayer.getPosPlayer());
            System.out.println("Tu vas trop loin, donc tu recules. T'es maintenant en case " + pPlayer.getPosPlayer() + ".");
        }
        if (pPlayer.getPosPlayer() < 0) {
            System.out.println("Ca commence mal... je te remets sur la case départ");
            pPlayer.setPosPlayer(0);
        }
        if (pPlayer.getPosPlayer() == 63) {
            System.out.println("OMG t'as fini !");
            winGame = true;
        }
    }

    // -------------------------------------- GESTION DE LA PARTIE --------------------------------------

    /**
     * Permet d'interagir avec une case. Vérifie aussi (si besoin) si le combat est gagné et affiche le menu de fin de tour.
     *
     * @param pPlayer joueur en action.
     */
    public void checkCase(Personnage pPlayer) {
        plateau.get(pPlayer.getPosPlayer()).interaction(pPlayer);
        checkFightResult(pPlayer);
        endTurnChoice(pPlayer);
    }

    /**
     * Permet de gérer le menu de fin de tour.
     *
     * @param pPlayer joueur en action.
     */
    private void endTurnChoice(Personnage pPlayer) {
        boolean endTurnExit = false;
        Scanner eventUser = new Scanner(System.in);
        while (!endTurnExit) {
            System.out.println("[entrée] pour passer au tour suivant, INFO pour voir tes stats");
            String temp = eventUser.nextLine();
            if (temp.equalsIgnoreCase("info")) {
                System.out.println(pPlayer);
            } else {
                endTurnExit = true;
            }
        }
    }

    /**
     * Permet de vérifier si un combat est gagné par le joueur ou s'il a fui.
     * S'il a gagné : remplace la case par une case CellCadavre.
     * S'il a fui : interagit avec la case sur laquelle il est arrivé.
     *
     * @param pPlayer joueur en action.
     */
    public void checkFightResult(Personnage pPlayer) {
        if (pPlayer.isExitFight()) {
            checkCase(pPlayer);
            pPlayer.setExitFight(false);
        }
        if (pPlayer.isWinFight()) {
            plateau.set(pPlayer.getPosPlayer(), new CellCadavre());
            pPlayer.setWinFight(false);
        }
    }

    /**
     * Permet de jouer au jeu. Le joueur se déplace puis il y a une vérification de case tant que la partie n'est pas gagnée et tant que le joueur a encore des points de vie.
     */
    public void playGame(Personnage pPlayer) {
        // test de la méthode et renvoie d'erreur si besoin
//        try {
//            while (pPlayer.getPosPlayer() < 64 && pPlayer.getPosPlayer() >= 0) {
//                movePlayer(pPlayer);
//                checkCase(pPlayer);
//                checkSpecialConditions(pPlayer);
//            }
//        } catch (PersonnageHorsPlateauException e) {
//            System.out.println("STOOOOOOOOOOOOOOP TU VAS TROP LOIN !!!!!!!!");
//        }
        while (!winGame && pPlayer.getLife() > 0) {
            movePlayer(pPlayer);
            checkCase(pPlayer);
        }
    }
}