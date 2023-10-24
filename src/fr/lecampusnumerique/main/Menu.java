package fr.lecampusnumerique.main;

import fr.lecampusnumerique.game.Game;
import fr.lecampusnumerique.offense.guerrier.Arme;
import fr.lecampusnumerique.offense.magicien.Sort;
import fr.lecampusnumerique.personnages.Guerrier;
import fr.lecampusnumerique.personnages.Magicien;
import fr.lecampusnumerique.personnages.Personnage;

import java.util.Scanner;

public class Menu {
    Personnage player;
    Game newGame;
    boolean exitStartMenu = false;
    boolean exitSousMenu = false;
    Scanner eventUser = new Scanner(System.in);

    public Menu() {

    }

    public void start() {
        displayBanner();
        displayStartMenu();
        while (!exitStartMenu) {
            if (getUserChoice() == 1) {
                player = createNewPlayer();
                while (!exitSousMenu) {
                    displaySousMenu();
                    int choice = getUserChoice();
                    if (choice == 1) {
                        player.displayStats();
                    }
                    if (choice == 2) {
                        updatePlayer();
                    }
                    if (choice == 3) {

                        exitSousMenu = true;
                        newGame = new Game(player);
                        newGame.playGame(player);
                        endGame();
                    }
                    quit();
                }
            } else {
                quit();
            }
        }
    }

    public Personnage createNewPlayer() {
        // récupération des entrées user
        System.out.println("Entrez le nom du personnage : ");
        String pName = eventUser.nextLine();
        System.out.println("Entrez la classe : ");
        String pType = eventUser.nextLine();
        // création perso en fonction du type
        if (pType.equalsIgnoreCase("guerrier")) {
            player = new Guerrier(pName);
            System.out.println("Personnage créé");
            return player;
        } else {
            player = new Magicien(pName);
            System.out.println("Personnage créé");
            return player;
        }
    }

    /**
     * Cette méthode va créer et ECRASER le player déjà créé.
     */
    public Personnage updatePlayer() {
        System.out.println("Modifier nom : ");
        String newName = eventUser.nextLine();
        System.out.println("Modifier type : ");
        String newType = eventUser.nextLine();
        if (newType.equalsIgnoreCase("guerrier")) {
            System.out.println("Personnage modifié");
            return player = new Guerrier(newName);

        } else {
            System.out.println("Personnage modifié");
            return player = new Magicien(newName);
        }
    }

    /**
     * Permet de récupérer le choix du joueur dans le menu (int uniquement)
     *
     * @return
     */
    public int getUserChoice() {
        Scanner newEventUser = new Scanner(System.in);
        return newEventUser.nextInt();
    }

    // ----- PARTIE GRAPHIQUE ------------------------------------------------------------------------------------
    public void displayBanner() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("--                                                                        --");
        System.out.println("--                                                                        --");
        System.out.println("--                    BIENVENUE DANS DONJONS & DRAGONS                    --");
        System.out.println("--                                                                        --");
        System.out.println("--                                                                        --");
        System.out.println("----------------------------------------------------------------------------");
    }

    public void displayStartMenu() {
        System.out.println("Que voulez-vous faire ?\n1 - Créer un nouveau joueur\n2 - Quitter");
        System.out.println("----------------------------------------------------------------------------");
    }

    public void displaySousMenu() {
        System.out.println("Que voulez-vous faire ?\n1 - Afficher les infos du joueur\n2 - Modifier les infos du joueur\n3 - Commencer la partie\n4 - Retour au menu principal");
    }

    // -------------------------------------- AUTRE --------------------------------------

    /**
     * Méthode utilisée pour quitter la boucle dans le menu principal
     */
    public void quit() {
        exitStartMenu = true;
    }

    public void endGame() {
        System.out.println("Que voulez-vous faire ?\n1 - Recommencer une partie\n2 - Quitter");
        int choice = getUserChoice();
        if (choice == 1) {
            int newChoice = getUserChoice();
            System.out.println("Que voulez-vous faire ?\n1 - Garder le même personnage\n2 - Créer un nouveau personnage");
            if (newChoice == 1) {
                player.setLife(player.getStartLife());
                player.setOffensive(player.getType().equalsIgnoreCase("guerrier") ? new Arme() : new Sort());
            } else {
                createNewPlayer();
            }
            newGame = new Game(player);
            newGame.playGame(player);
            endGame();
        } else if (choice == 2) {
            quit();
        }
    }
}