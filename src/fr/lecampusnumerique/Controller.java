package fr.lecampusnumerique;

import fr.lecampusnumerique.game.Game;
import fr.lecampusnumerique.main.ConnexionBDD;
import fr.lecampusnumerique.personnages.Guerrier;
import fr.lecampusnumerique.personnages.Magicien;
import fr.lecampusnumerique.personnages.Personnage;
import fr.lecampusnumerique.main.Menu;

public class Controller {
    protected Menu menu;
    protected Game newGame;
    protected Personnage player;
    protected ConnexionBDD myDB;

    public Controller() {
        menu = new Menu();
    }

    public void start() {
        menu.displayBanner();
        boolean stop = false;
        while (!stop) {
            switch (menu.startMenu()) {
                case 1 -> createNewPlayer();
                case 2 -> displayAllPlayers();
                case 3 -> updatePlayer();
//            case 4 -> choseExistantPlayer();
//            case 5 -> quit();
            }
            System.out.println(player);
        }


//        switch (menu.sousMenu()) {
//            case 1 -> player.displayStats();
//            case 2 -> updatePlayer();
//            case 3 -> newGame = new Game(player);
//        }

    }

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

    protected void updatePlayer() {
        String[] updatePlayerData = menu.createNewPlayerMenu();
        if (updatePlayerData[1].equalsIgnoreCase("guerrier")) {
            player = new Guerrier(updatePlayerData[0]);
        } else {
            player = new Magicien(updatePlayerData[0]);
        }
//        myDB.editHero(player);
    }

    protected void displayAllPlayers() {

    }


}
