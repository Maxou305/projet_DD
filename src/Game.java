import java.lang.reflect.GenericDeclaration;
import java.util.Scanner;

public class Game {
    private int plateau[];
    private int posPlayer;

    public Game() {
        plateau = new int[64];
        posPlayer = 0;
    }

    public int jetDados() {
        int dado = 1 + (int) (Math.random() * ((6 - 1) + 1));
        return dado;
    }

    public void movePlayer() {
        while (posPlayer < 63) {
            int resultat = jetDados();
            posPlayer += resultat;
            System.out.println("Vous avez fait " + resultat + " et avancé à sur la case " + posPlayer);
            if (posPlayer > 63) {
                posPlayer = 126 - posPlayer;
            }
            if (posPlayer == 63) {
                System.out.println("OMG t'as fini");
            }

        }
        System.out.println("Normalement tout va bien (je crois)");
    }

    public void playGame() {
        movePlayer();
    }
}