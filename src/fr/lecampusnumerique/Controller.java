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

/**
 * Classe Controller gérant la logique du programme (le code est pensé pour être au maximum sous format MVC).
 */
public class Controller {
    private Menu menu;
    boolean exitStartMenu = false;
    boolean exitSousMenu = false;
    private Game newGame;
    private Personnage player;
    private ConnexionBDD myDB = new ConnexionBDD();

    /**
     * Constructeur de Controller
     */
    public Controller() {
        menu = new Menu();
    }

    /**
     * Méthode initialisant le début du programme. Va permettre d'afficher les menus, créer les joueurs, les parties, jouer et recommencer.
     *
     * @throws SQLException
     * @throws PersonnageHorsPlateauException
     * @throws MauvaisChoixUtilisateur
     */
    public void start() throws SQLException, PersonnageHorsPlateauException, MauvaisChoixUtilisateur { // TODO ajouter la classe pour gérer les scanners
        if (myDB.Connect()) {
            menu.displayConnectedBDDMessage();
        } else {
            menu.displayNotConnectedBDDMessage();
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

    /**
     * Permet de créer un nouveau joueur et de le sauvegarder dans la BDD.
     */
    private void createNewPlayer() {
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
            menu.displayErrorMessage("Pas de save dans la BDD mais pas de souci tu peux game quand même");
        }
    }

    /**
     * Permet de mettre à jour les infos d'un joueur. Le lien avec la BDD est aussi effectué.
     *
     * @throws SQLException
     */
    private void updatePlayer() throws SQLException {
        String[] updatePlayerData = menu.displayUpdatePlayerMenu();
        if (updatePlayerData[1].equalsIgnoreCase("guerrier")) {
            player = new Guerrier(updatePlayerData[0]);
        } else {
            player = new Magicien(updatePlayerData[0]);
        }
        myDB.savePlayerInBDD(player);
        menu.displayUpdatedPlayerMessage();
    }

    /**
     * Permet d'afficher tous les joueurs depuis la BDD.
     *
     * @throws NullPointerException
     */
    private void displayAllPlayers() throws NullPointerException {
        try {
            menu.displayHeroesFromBDD(myDB.getHeroesFromBDD());
        } catch (NullPointerException e) {
            System.out.println("CA MARCHE PAS MDR");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    /**
     * Permet de choisir un joueur existant et de joueur une partie avec lui. Lien avec la BDD.
     *
     * @throws MauvaisChoixUtilisateur
     */
    private void chooseExistantPlayer() throws MauvaisChoixUtilisateur {
        ResultSet chosenPlayer;
        displayAllPlayers();
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

    /**
     * Permet de quitter le menu de départ.
     */
    private void quitStartMenu() {
        exitStartMenu = true;
    }

    /**
     * Permet de quitter le sous-menu.
     */
    private void quitSousMenu() {
        exitSousMenu = true;
    }

    /**
     * Permet de quitter le jeu.
     */
    private void quitGame() {
        exitStartMenu = true;
        exitSousMenu = true;
        menu.displayQuitGameMessage(player);
    }

    // ----------------- GAME ---------------------------------------------------------------

    /**
     * Permet de créer une partie.
     */
    public void startGame() {
        newGame = new Game(player);
        playGame(player);
    }

    /**
     * Permet de jouer au jeu. Le joueur se déplace puis il y a une vérification de case tant que la partie n'est pas gagnée et tant que le joueur a encore des points de vie.
     *
     * @param pPlayer joueur
     */
    private void playGame(Personnage pPlayer) {
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

    /**
     * Permet de gérer la fin de partie. Méthode appelée une fois que le joueur est sur la case 63 et permet de recommencer une partie ou de quitter le jeu.
     *
     * @throws SQLException
     * @throws PersonnageHorsPlateauException
     * @throws MauvaisChoixUtilisateur
     */
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

    /**
     * Permet de recommencer une partie.
     *
     * @throws PersonnageHorsPlateauException
     * @throws SQLException
     * @throws MauvaisChoixUtilisateur
     */
    private void restartGame() throws PersonnageHorsPlateauException, SQLException, MauvaisChoixUtilisateur {
        newGame = new Game(player);
        playGame(player);
        endGame();
    }
}