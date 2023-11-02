package fr.lecampusnumerique;

import fr.lecampusnumerique.equipements.offense.guerrier.Arme;
import fr.lecampusnumerique.equipements.offense.guerrier.Epee;
import fr.lecampusnumerique.equipements.offense.guerrier.Massue;
import fr.lecampusnumerique.equipements.offense.magicien.BouleDeFeu;
import fr.lecampusnumerique.equipements.offense.magicien.Eclair;
import fr.lecampusnumerique.equipements.offense.magicien.Sort;
import fr.lecampusnumerique.exceptions.MauvaisChoixUtilisateur;
import fr.lecampusnumerique.exceptions.PersonnageHorsPlateauException;
import fr.lecampusnumerique.exceptions.ProblemeConnexion;
import fr.lecampusnumerique.game.Game;
import fr.lecampusnumerique.main.ConnexionBDD;
import fr.lecampusnumerique.personnages.Guerrier;
import fr.lecampusnumerique.personnages.Magicien;
import fr.lecampusnumerique.personnages.Personnage;
import fr.lecampusnumerique.main.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    private Menu menu;
    boolean exitStartMenu = false;
    boolean exitSousMenu = false;
    private Game newGame;
    private Personnage player;
    private ConnexionBDD myDB = new ConnexionBDD();

    public Controller() {
        menu = new Menu();
    }

    public void start() throws SQLException, PersonnageHorsPlateauException, MauvaisChoixUtilisateur, ProblemeConnexion {
        if (myDB.Connect()) {
            menu.displayConnectedBDDMessage();
        } else {
            menu.displayNotConnectedBDDMessage();
            throw new ProblemeConnexion();
        }
        menu.displayBanner();
        while (!exitStartMenu) {
            switch (menu.displayStartMenu()) {
                case 1 -> {
                    createNewPlayer();
                    quitStartMenu();
                }
                case 2 -> displayAllPlayers();
                case 3 -> updatePlayer();
                case 4 -> {
                    chooseExistantPlayer();
                    quitStartMenu();
                }
                case 5 -> quitGame();
                default -> throw new MauvaisChoixUtilisateur();
            }
        }
        while (!exitSousMenu) {
            switch (menu.displaySousMenu()) {
                case 1 -> player.displayStats();
                case 2 -> updatePlayer();
                case 3 -> {
                    startGame();
                    endGame();
                }
                case 4 -> start();
                case 5 -> {
                    exitStartMenu = false;
                    quitSousMenu();
                }
            }
        }
    }

    // ----------------- PLAYER ---------------------------------------------------------------

    private void createNewPlayer() throws SQLException {
        String[] newPlayerData = menu.displayCreateNewPlayerMenu();
        if (newPlayerData[1].equalsIgnoreCase("guerrier")) {
            player = new Guerrier(newPlayerData[0]);
            menu.displayCreatedPlayerMessage();
        } else {
            player = new Magicien(newPlayerData[0]);
            menu.displayCreatedPlayerMessage();
        }
        try {
            myDB.savePlayerInBDD(player);
            menu.displaySavePlayerinBDDMessage();
        } catch (SQLException e) {
            menu.displayErrorMessage("Pas de save dans la BDD mais pas de souci tu peux game");
        }
    }

    private void updatePlayer() throws SQLException {
        String[] updatePlayerData = menu.displayUpdatePlayerMenu();
        if (updatePlayerData[1].equalsIgnoreCase("guerrier")) {
            player = new Guerrier(updatePlayerData[0]);
        } else {
            player = new Magicien(updatePlayerData[0]);
        }
        menu.displayUpdatedPlayerMessage();
        myDB.savePlayerInBDD(player);
    }

    private void displayAllPlayers() throws NullPointerException {
        try {
            myDB.displayHeroes();
        } catch (NullPointerException e) {
            System.out.println("CA MARCHE PAS MDR");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    private void chooseExistantPlayer() throws MauvaisChoixUtilisateur {
        ResultSet chosenPlayer;
        try {
            myDB.displayHeroesID();
        } catch (SQLException e) {
            e.getMessage();
        }
        try {
            chosenPlayer = myDB.getHeroByID(menu.displayChooseExistantPlayerMenu());
        } catch (SQLException e) {
            throw new MauvaisChoixUtilisateur();
        }
        try {
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
            e.getMessage();
        }
        menu.displayChargedPlayerMessage(player);
    }

    private void quitStartMenu() {
        exitStartMenu = true;
    }

    private void quitSousMenu() {
        exitSousMenu = true;
    }

    private void quitGame() {
        exitStartMenu = true;
        exitSousMenu = true;
        menu.displayQuitGameMessage(player);
    }

    // ----------------- GAME ---------------------------------------------------------------
    public void startGame() throws PersonnageHorsPlateauException {
        newGame = new Game(player);
        playGame(player);
    }

    /**
     * Permet de jouer au jeu. Le joueur se déplace puis il y a une vérification de case tant que la partie n'est pas gagnée et tant que le joueur a encore des points de vie.
     */
    private void playGame(Personnage pPlayer) throws PersonnageHorsPlateauException {
//         test de la méthode et renvoie d'erreur si besoin
        while (!newGame.isWinGame() && pPlayer.getLife() > 0) {
            try {
                newGame.movePlayer(pPlayer);
            } catch (PersonnageHorsPlateauException e) {
                pPlayer.moveBack();
            }
            newGame.checkCase(player);
            if (player.getPosition() == 63) {
                menu.displayWinMessage();
                newGame.setWinGame(true);
            }
            menu.displayEndTurnMenu(player);
        }
    }

    private void endGame() throws SQLException, PersonnageHorsPlateauException, MauvaisChoixUtilisateur {
        switch (menu.displayEndGameMenu()) {
            case 1 -> {
                player.setLife(player.getStartLife());
                player.setOffensive(player.getType().equalsIgnoreCase("guerrier") ? new Arme() : new Sort());
                restartGame();
            }
            case 2 -> {
                chooseExistantPlayer();
                restartGame();
            }
            case 3 -> {
                createNewPlayer();
                restartGame();
            }
            case 4 -> quitGame();
        }
    }

    private void restartGame() throws PersonnageHorsPlateauException, SQLException, MauvaisChoixUtilisateur {
        newGame = new Game(player);
        playGame(player);
        endGame();
    }
}