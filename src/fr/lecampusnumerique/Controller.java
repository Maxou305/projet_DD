package fr.lecampusnumerique;

import fr.lecampusnumerique.equipements.offense.guerrier.Epee;
import fr.lecampusnumerique.equipements.offense.guerrier.Massue;
import fr.lecampusnumerique.equipements.offense.magicien.BouleDeFeu;
import fr.lecampusnumerique.equipements.offense.magicien.Eclair;
import fr.lecampusnumerique.exceptions.MauvaisChoixUtilisateur;
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
    protected Game newGame;
    protected Personnage player;
    protected ConnexionBDD myDB = new ConnexionBDD();

    public Controller() {
        menu = new Menu();
        myDB.Connect();
    }

    public void start() throws SQLException {
        menu.displayBanner();
        boolean stop = false;
        boolean stop2 = false;
        while (!stop) {
            switch (menu.startMenu()) {
                case 1 -> {
                    createNewPlayer();
                    stop = true;
                }
                case 2 -> displayAllPlayers();
                case 3 -> updatePlayer();
                case 4 -> {
                    chooseExistantPlayer();
                    stop = true;
                }
//              case 5 -> quit();
            }
        }
        while (!stop2)
            switch (menu.sousMenu()) {
                case 1 -> player.displayStats();
                case 2 -> updatePlayer();
                case 3 -> {
                    startGame();
                }
//              case 4 -> quit();
            }

    }

    // ----------------- PLAYER ---------------------------------------------------------------

    protected void createNewPlayer() {
        String[] newPlayerData = menu.createNewPlayerMenu();
        if (newPlayerData[1].equalsIgnoreCase("guerrier")) {
            player = new Guerrier(newPlayerData[0]);
            System.out.println("Personnage créé");
        } else {
            player = new Magicien(newPlayerData[0]);
            System.out.println("Personnage créé");
        }
//        myDB.createHero(player);
    }

    protected void updatePlayer() throws SQLException {
        String[] updatePlayerData = menu.updatePlayerMenu();
        if (updatePlayerData[1].equalsIgnoreCase("guerrier")) {
            player = new Guerrier(updatePlayerData[0]);
        } else {
            player = new Magicien(updatePlayerData[0]);
        }
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
        } catch (SQLException e) {
            System.out.println("Le truc a pas chargé");
        }
    }

    // ----------------- GAME ---------------------------------------------------------------
    public void startGame(){
        newGame = new Game(player);
    }



}
