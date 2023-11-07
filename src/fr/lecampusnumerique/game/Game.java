package fr.lecampusnumerique.game;

import fr.lecampusnumerique.exceptions.PersonnageHorsPlateauException;
import fr.lecampusnumerique.game.ennemis.Dragon;
import fr.lecampusnumerique.game.ennemis.Gobelin;
import fr.lecampusnumerique.game.ennemis.Sorcier;
import fr.lecampusnumerique.game.potions.PopoBig;
import fr.lecampusnumerique.game.potions.PopoMini;
import fr.lecampusnumerique.equipements.offense.guerrier.Epee;
import fr.lecampusnumerique.equipements.offense.guerrier.Massue;
import fr.lecampusnumerique.equipements.offense.magicien.BouleDeFeu;
import fr.lecampusnumerique.equipements.offense.magicien.Eclair;
import fr.lecampusnumerique.personnages.Personnage;

import java.util.ArrayList;

/**
 * Classe Game incluant toutes les règles du jeu.
 */
public class Game {
    private final ArrayList<iCell> plateau;

    private boolean winGame = false;

    /**
 * Constructeur de la classe Game. Va créer un plateau de jeu format ArrayList et mettre le joueur sur la case 0.
     */
    public Game(Personnage pPlayer) {
        plateau = new ArrayList<>();
        pPlayer.setPosition(0);
//        initCasesPlateau();
        initRandomCasesPlateau();
    }

    /**
     * Méthode permettant de gérer le lancer de dé. Construit grâce à un random compris en tre 1 et 6.
     *
     * @return valeur du jet de dés.
     */
    public int jetDado() {
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
     * Permet de faire avancer le joueur en fonction d'un jet de dés.
     *
     * @param pPlayer joueur devant se déplacer
     */
    public void movePlayer(Personnage pPlayer) throws PersonnageHorsPlateauException {
        int result = jetDado();
        pPlayer.setPosition(pPlayer.getPosition() + result);
        System.out.println("Vous avez fait " + result + " et avancé sur la case " + pPlayer.getPosition());
        if (pPlayer.getPosition() > 63) {
            throw new PersonnageHorsPlateauException();
        }
        if (pPlayer.getPosition() < 0) {
            System.out.println("Ca commence mal... je te remets sur la case départ");
            pPlayer.setPosition(0);
        }
    }

    // ----- GESTION DE LA PARTIE ------------------------------------------------------------------------------------

    /**
     * Permet d'interagir avec une case. Vérifie aussi (si besoin) si le combat est gagné et affiche le menu de fin de tour.
     *
     * @param pPlayer joueur en action.
     */
    public void checkCase(Personnage pPlayer) {
        plateau.get(pPlayer.getPosition()).interaction(pPlayer);
        checkFightResult(pPlayer);
    }

    /**
     * Permet de vérifier si un combat est gagné par le joueur ou s'il a fui.
     * S'il a gagné : remplace la case par une case CellCadavre.
     * S'il a fui : interagit avec la case sur laquelle il est arrivé.
     *
     * @param pPlayer joueur
     */
    public void checkFightResult(Personnage pPlayer) {
        if (pPlayer.isExitFight()) {
            pPlayer.setExitFight(false);
            checkCase(pPlayer);
        }
        if (pPlayer.isWinFight()) {
            plateau.set(pPlayer.getPosition(), new CellCadavre());
            pPlayer.setWinFight(false);
        }
    }

    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------
    public boolean isWinGame() {
        return winGame;
    }

    public void setWinGame(boolean winGame) {
        this.winGame = winGame;
    }
}