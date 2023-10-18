import java.util.Scanner;

public class Menu {
    Character player;
    Game newGame;
    boolean exitStartMenu = false;
    boolean exitSousMenu = false;

    public Menu() {

    }

    public void start() {
        displayBanner();
        // Start Menu
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
                        player.modifyStats();
                    }
                    if (choice == 3) {
                        exitSousMenu = true;
                        startGame();
                        newGame.playGame();
                        endGame();
                    }
                    quit();
                }
            } else {
                quit();
            }
        }
    }

    public Character createNewPlayer() {
        Scanner eventUser = new Scanner(System.in);
        System.out.println("Entrez le nom du personnage : ");
        String pName = eventUser.nextLine();
        System.out.println("Entrez la classe : ");
        String pType = eventUser.nextLine();
        Character player = new Character(pName, pType);
        System.out.println("Personnage créé");
        return player;
    }

    public int getUserChoice() {
        Scanner newEventUser = new Scanner(System.in);
        int userChoice = newEventUser.nextInt();
        return userChoice;
    }

    public void displayBanner() {
        System.out.println("--------------------- BIENVENUE DANS DONJONS & DRAGONS ---------------------");
    }

    public void displayStartMenu() {
        System.out.println("Que voulez-vous faire ?\n1 - Créer un nouveau joueur\n2 - Quitter");
        System.out.println("----------------------------------------------------------------------------");
    }

    public void displaySousMenu() {
        System.out.println("Que voulez-vous faire ?\n1 - Afficher les infos du joueur\n2 - Modifier les infos du joueur\n3 - Commencer la partie\n4 - Retour au menu principal");
    }

    public void quit() {
        exitStartMenu = true;
    }

    public void startGame() {
        newGame = new Game();
    }

    public void endGame() {
        System.out.println("Que voulez-vous faire ?\n1 - Recommencer une partie\n2 - Quitter");
        int choice = getUserChoice();
        if (choice == 1) {
            startGame();
            newGame.playGame();
            endGame();
        } else if (choice == 2) {
            quit();
        }
    }
}