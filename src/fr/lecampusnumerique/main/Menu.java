package fr.lecampusnumerique.main;

import fr.lecampusnumerique.personnages.Personnage;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu  {
    Scanner eventUser = new Scanner(System.in);
    protected ConnexionBDD myDB = new ConnexionBDD();

    public Menu() {
        myDB.Connect();
    }

//    public void start() throws SQLException, PersonnageHorsPlateauException {
//        displayBanner();
//        while (!exitStartMenu) {
//            displayStartMenu();
//            int startMenuChoice = getUserChoice();
//            if (startMenuChoice == 1) {
//                createNewPlayer();
//                while (!exitSousMenu) {
//                    displaySousMenu();
//                    int sousMenuChoice = getUserChoice();
//                    if (sousMenuChoice == 1) {
//                        player.displayStats();
//                    }
//                    if (sousMenuChoice == 2) {
//                        updatePlayer();
//                        myDB.editHero(player);
//                    }
//                    if (sousMenuChoice == 3) {
//                        exitSousMenu = true;
//                        newGame = new Game(player);
//                        newGame.playGame(player);
//                        endGame();
//                    }
//                    quit();
//                }
//            }
//            if (startMenuChoice == 2) {
//                myDB.displayHeroes();
//            }
//            if (startMenuChoice == 3) {
//                editPlayer();
//            }
//            if (startMenuChoice == 4) {
//                chooseExistantPlayer();
//                newGame = new Game(player);
//                newGame.playGame(player);
//                endGame();
//            }
//            if (startMenuChoice == 5) {
//                quit();
//            }
//        }
//    }

    /**
     * Cette méthode va créer et ECRASER le player déjà créé.
     */
    public String[] updatePlayerMenu() {
        System.out.println("Modifier nom : ");
        String newName = eventUser.next();
        System.out.println("Modifier type : ");
        String newType = eventUser.next();
        String[] arr = {newName, newType};
        return arr;
    }


    public String[] createNewPlayerMenu() {
        System.out.println("Entrez le nom du personnage : ");
        String pName = eventUser.next();
        System.out.println("Entrez la classe : ");
        String pType = eventUser.next();
        String[] arr = {pName, pType};
        return arr;
    }

    public int chooseExistantPlayerMenu() throws SQLException {
        boolean chosenPlayerState = false;
        while (!chosenPlayerState) {
            try {
                myDB.displayHeroesID();
                System.out.println("Quel héros veux-tu ?");
                chosenPlayerState = true;
            } catch (SQLException e) {
                System.out.println("Ce héros n'existe pas !");
            } catch (NullPointerException e) {
                e.getMessage();
            }
        }
        return eventUser.nextInt();
    }

    public void printMessage(String pMessage) {
        System.out.println(pMessage);
    }

//    public void editPlayer() throws SQLException {
//        boolean playerUpdated = false;
//
//        myDB.displayHeroesID();
//
//        System.out.println("Quel héros veux-tu modifier ?");
//        int userChoice = eventUser.nextInt();
//
//        System.out.println("Entrez le nouveau nom :");
//        String newName = eventUser.next();
//        System.out.println("Entrez la nouvelle classe :");
//        String newType = eventUser.next().toLowerCase();
//
//        while (!playerUpdated) {
//            if (newType.equalsIgnoreCase("guerrier")) {
//                player = new Guerrier(newName);
//            }
//            if (newType.equalsIgnoreCase("magicien")) {
//                player = new Magicien(newName);
//            }
//            player.setId(userChoice);
//            myDB.editHero(player);
//            playerUpdated = true;
//        }
//    }

    public void displayBanner() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("--                                                                        --");
        System.out.println("--                                                                        --");
        System.out.println("--                    BIENVENUE DANS DONJONS & DRAGONS                    --");
        System.out.println("--                                                                        --");
        System.out.println("--                                                                        --");
        System.out.println("----------------------------------------------------------------------------");
    }

    public int startMenu() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Que voulez-vous faire ?\n1 - Créer un nouveau joueur\n2 - Afficher les joueurs\n3 - Modifier un joueur\n4 - Choisir un joueur existant\n5 - Quitter le jeu");
        System.out.println("----------------------------------------------------------------------------");
        return eventUser.nextInt();
    }

    public int sousMenu() {
        System.out.println("Que voulez-vous faire ?\n1 - Afficher les infos du joueur\n2 - Modifier les infos du joueur\n3 - Commencer la partie\n4 - Retour au menu principal\n5 - Quitter");
        return eventUser.nextInt();
    }
    /**
     * Permet de gérer le menu de fin de tour.
     *
     * @param pPlayer joueur en action.
     */
    public void endTurnMenu(Personnage pPlayer) {
        boolean endTurnExit = false;
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

    public int endGameMenu(){
        System.out.println("Que voulez-vous faire ?\n1 - Recommencer une partie avec le même héros\n2 - Recommencer une partie en choisissant un nouvel héros\n3 - Recommencer une partie en créant un nouvel héros\n4 - Quitter le jeu");
        return eventUser.nextInt();
    }



    public void winMenu(){
        System.out.println("OMG t'as fini !");
    }

}