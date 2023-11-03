package fr.lecampusnumerique.main;

import fr.lecampusnumerique.personnages.Personnage;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner eventUser;


    public Menu() {
        eventUser = new Scanner(System.in);
    }

    // ----------------- DISPLAY MENU ---------------------------------------------------------------

    /**
     * Permet d'afficher le menu de modification de personnage.
     */
    public String[] displayUpdatePlayerMenu() {
        System.out.println("Modifier nom : ");
        String newName = eventUser.next();
        System.out.println("Modifier type : ");
        String newType = eventUser.next();
        String[] arr = {newName, newType};
        return arr;
    }

    /**
     * Permet d'afficher le menu de modification de personnage.
     *
     * @return
     */
    public String[] displayCreateNewPlayerMenu() {
        System.out.println("Entrez le nom du personnage : ");
        String pName = eventUser.next();
        System.out.println("Entrez la classe : ");
        String pType = eventUser.next();
        String[] arr = {pName, pType};
        return arr;
    }

    /**
     * Permet d'afficher le menu de choix de personnage existant.
     *
     * @return
     * @throws SQLException
     */

    public int displayChooseExistantPlayerMenu() throws SQLException {
        boolean chosenPlayerState = false;
        while (!chosenPlayerState) {
            try {
                System.out.println("Quel héros veux-tu ?");
                chosenPlayerState = true;
            } catch (NullPointerException e) {
                System.out.println("Ce héros n'existe pas !");
            }
        }
        return eventUser.nextInt();
    }

    public int displayStartMenu() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Que voulez-vous faire ?\n1 - Créer un nouveau joueur\n2 - Afficher les joueurs\n3 - Modifier un joueur\n4 - Choisir un joueur existant\n5 - Quitter le jeu");
        System.out.println("-------------------------------------------------------------");
        return eventUser.nextInt();
    }

    public int displaySousMenu() {
        System.out.println("Que voulez-vous faire ?\n1 - Afficher les infos du joueur\n2 - Modifier les infos du joueur\n3 - Commencer la partie\n4 - Retour au menu principal\n5 - Quitter");
        return eventUser.nextInt();
    }

    /**
     * Permet de gérer le menu de fin de tour.
     *
     * @param pPlayer joueur en action.
     */
    public void displayEndTurnMenu(Personnage pPlayer) {
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

    public int displayEndGameMenu() {
        System.out.println("Que voulez-vous faire ?\n1 - Recommencer une partie avec le même héros\n2 - Recommencer une partie en choisissant un nouvel héros\n3 - Recommencer une partie en créant un nouvel héros\n4 - Quitter le jeu");
        return eventUser.nextInt();
    }

    // ----------------- DISPLAY MESSAGES ---------------------------------------------------------------

    public void displayBanner() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("--                                                                        --");
        System.out.println("--                                                                        --");
        System.out.println("--                    BIENVENUE DANS DONJONS & DRAGONS                    --");
        System.out.println("--                                                                        --");
        System.out.println("--                                                                        --");
        System.out.println("----------------------------------------------------------------------------");
    }

    public void displayWinMessage() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("--                                                                        --");
        System.out.println("--            BRAVO CHAMPION(E), TU AS FAIT PREUVE DE COURAGE             --");
        System.out.println("--                      TU AS FAIS PREUVE DE COURAGE                      --");
        System.out.println("--                      ET VAINCU TOUS TES ENNEMIS !                      --");
        System.out.println("--                                                                        --");
        System.out.println("----------------------------------------------------------------------------");
    }

    public void displayChargedPlayerMessage(Personnage pPlayer) {
        System.out.println(pPlayer.getName() + " est chargé(e)");
    }

    public void displayUpdatedPlayerMessage() {
        System.out.println("Personnage modifié");
    }

    public void displayCreatedPlayerMessage() {
        System.out.println("Personnage créé");
    }

    public void displaySavePlayerinBDDMessage() {
        System.out.println("Personnage sauvegardé dans la BDD");
    }

    public void displayQuitGameMessage(Personnage pPlayer) {
        System.out.println("Au revoir, " + pPlayer.getName());
    }

    public void displayConnectedBDDMessage() {
        System.out.println("BDD CONNECTEE");
    }

    public void displayNotConnectedBDDMessage() {
        System.out.println("BDD NON CONNECTEE");
    }

    public void displayErrorMessage(String pMessage) {
        System.out.println(pMessage);
    }

    // ----------------- DISPLAY BDD ---------------------------------------------------------------

    public void displayHeroesFromBDD(ArrayList pList) {
        System.out.println(pList);
    }
}