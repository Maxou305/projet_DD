import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // gestion event clavier
        Scanner eventUser = new Scanner(System.in);
        System.out.println("Nom : ");
        String pName = eventUser.nextLine();
        System.out.println("Type : ");
        String pType = eventUser.nextLine();

        Character player = new Character(pName, pType);
        System.out.print(player);
    }
}