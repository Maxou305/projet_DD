package fr.lecampusnumerique;

import fr.lecampusnumerique.equipements.offense.guerrier.Arme;
import fr.lecampusnumerique.equipements.offense.guerrier.Epee;
import fr.lecampusnumerique.equipements.offense.guerrier.Massue;
import fr.lecampusnumerique.equipements.offense.magicien.BouleDeFeu;
import fr.lecampusnumerique.equipements.offense.magicien.Eclair;
import fr.lecampusnumerique.equipements.offense.magicien.Sort;
import fr.lecampusnumerique.exceptions.MauvaisChoixUtilisateur;
import fr.lecampusnumerique.exceptions.PersonnageHorsPlateauException;
import fr.lecampusnumerique.game.Game;
import fr.lecampusnumerique.main.ConnexionBDD;
import fr.lecampusnumerique.personnages.Guerrier;
import fr.lecampusnumerique.personnages.Magicien;
import fr.lecampusnumerique.personnages.Personnage;
import fr.lecampusnumerique.main.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    protected Menu menu;
    boolean exitStartMenu = false;
    boolean exitSousMenu = false;
    protected Game newGame;
    protected Personnage player;
    protected ConnexionBDD myDB = new ConnexionBDD();

    public Controller() {
        menu = new Menu();
        myDB.Connect();
    }

    public void start() throws SQLException, PersonnageHorsPlateauException, MauvaisChoixUtilisateur {
        menu.displayBanner();
        while (!exitStartMenu) {
            switch (menu.startMenu()) {
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
            switch (menu.sousMenu()) {
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

    protected void createNewPlayer() throws SQLException {
        String[] newPlayerData = menu.createNewPlayerMenu();
        if (newPlayerData[1].equalsIgnoreCase("guerrier")) {
            player = new Guerrier(newPlayerData[0]);
            menu.printMessage("Personnage créé");
        } else {
            player = new Magicien(newPlayerData[0]);
            menu.printMessage("Personnage créé");
        }
        myDB.saveHeroInDB(player);
    }

    protected void updatePlayer() throws SQLException {
        String[] updatePlayerData = menu.updatePlayerMenu();
        if (updatePlayerData[1].equalsIgnoreCase("guerrier")) {
            player = new Guerrier(updatePlayerData[0]);
        } else {
            player = new Magicien(updatePlayerData[0]);
        }
        menu.printMessage("Personnage modifié");
        myDB.saveHeroInDB(player);
    }

    protected void displayAllPlayers() throws SQLException {
        myDB.displayHeroes();
    }

    protected void chooseExistantPlayer() {
        try {
            ResultSet chosenPlayer = myDB.getHeroByID(menu.chooseExistantPlayerMenu());
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
            menu.printMessage("Personnage chargé");
        } catch (SQLException e) {
            menu.printMessage("Le truc a pas chargé");
        }

    }

    protected void quitStartMenu() {
        exitStartMenu = true;
    }

    protected void quitSousMenu() {
        exitSousMenu = true;
    }

    protected void quitGame() {
        exitSousMenu = true;
    }

    // ----------------- GAME ---------------------------------------------------------------
    public void startGame() throws PersonnageHorsPlateauException {
        newGame = new Game(player);
        playGame(player);
    }

    /**
     * Permet de jouer au jeu. Le joueur se déplace puis il y a une vérification de case tant que la partie n'est pas gagnée et tant que le joueur a encore des points de vie.
     */
    public void playGame(Personnage pPlayer) throws PersonnageHorsPlateauException {
//         test de la méthode et renvoie d'erreur si besoin
        while (!newGame.isWinGame() && pPlayer.getLife() > 0) {
            try {
                newGame.movePlayer(pPlayer);
                if (player.getPosition() == 63) {
                    menu.winMenu();
                    newGame.setWinGame(true);
                }
            } catch (PersonnageHorsPlateauException e) {
                pPlayer.moveBack();
            }
            newGame.checkCase(player);
            menu.endTurnMenu(player);
        }
    }

    public void endGame() throws SQLException, PersonnageHorsPlateauException {
        switch (menu.endGameMenu()) {
            case 1 -> {
                player.setLife(player.getStartLife());
                player.setOffensive(player.getType().equalsIgnoreCase("guerrier") ? new Arme() : new Sort());
                zob();
            }
            case 2 -> {
                chooseExistantPlayer();
                zob();
            }
            case 3 -> {
                createNewPlayer();
                zob();
            }
            case 4 -> quitGame();
        }
    }

    private void zob() throws PersonnageHorsPlateauException, SQLException {
        newGame = new Game(player);
        playGame(player);
        endGame();
    }
}