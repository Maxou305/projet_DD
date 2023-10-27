package fr.lecampusnumerique.main;

import fr.lecampusnumerique.exceptions.PersonnageHorsPlateauException;
import fr.lecampusnumerique.game.Game;
import fr.lecampusnumerique.offense.guerrier.Arme;
import fr.lecampusnumerique.offense.guerrier.Epee;
import fr.lecampusnumerique.offense.guerrier.Massue;
import fr.lecampusnumerique.offense.magicien.BouleDeFeu;
import fr.lecampusnumerique.offense.magicien.Eclair;
import fr.lecampusnumerique.offense.magicien.Sort;
import fr.lecampusnumerique.personnages.Guerrier;
import fr.lecampusnumerique.personnages.Magicien;
import fr.lecampusnumerique.personnages.Personnage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    Personnage player;
    Game newGame;
    boolean exitStartMenu = false;
    boolean exitSousMenu = false;
    Scanner eventUser = new Scanner(System.in);
    ConnexionBDD myDB;

    public Menu() {
        myDB = new ConnexionBDD();
        boolean bddConnected = myDB.Connect();
        if (bddConnected) {
            // Do something
        }
    }

    public void start() throws SQLException, PersonnageHorsPlateauException {
        displayBanner();
        while (!exitStartMenu) {
            displayStartMenu();
            int startMenuChoice = getUserChoice();
            if (startMenuChoice == 1) {
                createNewPlayer();
                while (!exitSousMenu) {
                    displaySousMenu();
                    int sousMenuChoice = getUserChoice();
                    if (sousMenuChoice == 1) {
                        player.displayStats();
                    }
                    if (sousMenuChoice == 2) {
                        updatePlayer();
                        myDB.editHero(player);
                    }
                    if (sousMenuChoice == 3) {
                        exitSousMenu = true;
                        newGame = new Game(player);
                        newGame.playGame(player);
                        endGame();
                    }
                    quit();
                }
            }
            if (startMenuChoice == 2) {
                myDB.displayHeroes();
            }
            if (startMenuChoice == 3) {
                editPlayer();
            }
            if (startMenuChoice == 4) {
                chooseExistantPlayer();
                newGame = new Game(player);
                newGame.playGame(player);
                endGame();
            }
            if (startMenuChoice == 5) {
                quit();
            }
        }
    }

    /**
     * Cette méthode va créer et ECRASER le player déjà créé.
     */
    public void updatePlayer() {
        System.out.println("Modifier nom : ");
        String newName = eventUser.nextLine();
        System.out.println("Modifier type : ");
        String newType = eventUser.nextLine();
        if (newType.equalsIgnoreCase("guerrier")) {
            System.out.println("Personnage modifié");
            player = new Guerrier(newName);

        } else {
            System.out.println("Personnage modifié");
            player = new Magicien(newName);
        }
    }

    /**
     * Permet de récupérer le choix du joueur dans le menu (int uniquement)
     *
     * @return la demande du menu (int)
     */
    public int getUserChoice() {
        Scanner newEventUser = new Scanner(System.in);
        return newEventUser.nextInt();
    }

    public void createNewPlayer() throws SQLException {
        // récupération des entrées user
        System.out.println("Entrez le nom du personnage : ");
        String pName = eventUser.nextLine();
        System.out.println("Entrez la classe : ");
        String pType = eventUser.nextLine();
        // création perso en fonction du type
        if (pType.equalsIgnoreCase("guerrier")) {
            player = new Guerrier(pName);
            System.out.println("Personnage créé");
        } else {
            player = new Magicien(pName);
            System.out.println("Personnage créé");
        }
        // "upload" dans la BDD
        myDB.createHero(player);
    }

    public void chooseExistantPlayer() throws SQLException {
        boolean chosenPlayerState = false;
        while (!chosenPlayerState) {
            try {
                myDB.displayHeroesID();
                System.out.println("Quel héros veux-tu ?");
                chosenPlayerState = true;
            } catch (SQLException e) {
            }
        }
        int userChoice = eventUser.nextInt();
        try {
            ResultSet chosenPlayer = myDB.getHeroByID(userChoice);
            while (chosenPlayer.next()) {

                if (chosenPlayer.getString("type").equalsIgnoreCase("guerrier")) {
                    player = new Guerrier(chosenPlayer.getString("name"));
                    player.setLife(chosenPlayer.getInt("life"));
                    if (chosenPlayer.getString("offensive").equalsIgnoreCase("Gourdin")) {
                        player.setOffensive(new Massue());
                    }
                    if (chosenPlayer.getString("offensive").equalsIgnoreCase("Excalibur")) {
                        player.setOffensive(new Epee());
                    }
                } else if (chosenPlayer.getString("type").equalsIgnoreCase("magicien")) {
                    player = new Magicien(chosenPlayer.getString("name"));
                    player.setLife(chosenPlayer.getInt("life"));
                    if (chosenPlayer.getString("offensive").equalsIgnoreCase("Pika Pika")) {
                        player.setOffensive(new Eclair());
                    }
                    if (chosenPlayer.getString("offensive").equalsIgnoreCase("Resto mexicain trop épicé")) {
                        player.setOffensive(new BouleDeFeu());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Le truc a pas chargé");
        }
        System.out.println("-------------------\nPersonnage chargé\n-------------------");
    }

    public void editPlayer() throws SQLException {
        boolean playerUpdated = false;

        myDB.displayHeroesID();

        System.out.println("Quel héros veux-tu modifier ?");
        int userChoice = eventUser.nextInt();

        System.out.println("Entrez le nouveau nom :");
        String newName = eventUser.next();
        System.out.println("Entrez la nouvelle classe :");
        String newType = eventUser.next().toLowerCase();

        while (!playerUpdated) {
            if (newType.equalsIgnoreCase("guerrier")) {
                player = new Guerrier(newName);
            }
            if (newType.equalsIgnoreCase("magicien")) {
                player = new Magicien(newName);
            }
            player.setId(userChoice);
            myDB.editHero(player);
            playerUpdated = true;
        }
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
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Que voulez-vous faire ?\n1 - Créer un nouveau joueur\n2 - Afficher les joueurs\n3 - Modifier un joueur\n4 - Choisir un joueur existant\n5 - Quitter");
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

    public void endGame() throws SQLException, PersonnageHorsPlateauException {
        System.out.println("Que voulez-vous faire ?\n1 - Recommencer une partie\n2 - Quitter");
        int choice = getUserChoice();
        if (choice == 1) {
            int newChoice = getUserChoice();
            System.out.println("Que voulez-vous faire ?\n1 - Garder le même personnage\n2 - Créer un nouveau personnage");
            if (newChoice == 1) {
                player.setLife(player.getStartLife());
                player.setOffensive(player.getType().equalsIgnoreCase("guerrier") ? new Arme() : new Sort());
            } else {
                myDB.createHero(player);
            }
            newGame = new Game(player);
            newGame.playGame(player);
            endGame();
        } else if (choice == 2) {
            quit();
        }
    }
}