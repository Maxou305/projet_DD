package fr.lecampusnumerique.offense;

import fr.lecampusnumerique.game.Cell;
import fr.lecampusnumerique.personnages.Personnage;

import java.util.Scanner;

public abstract class EquipementOffensif implements Cell {
    private int value;
    private String name;
    private String usableBy;

    protected EquipementOffensif(String pName) {
        name = pName;
    }


    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------

    public abstract String getType();

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsableBy() {
        return usableBy;
    }

    public void setUsableBy(String usableBy) {
        this.usableBy = usableBy;
    }

    public void interaction(Personnage player) {
        Scanner eventUser = new Scanner(System.in);
        System.out.println("Vous entrez dans la pièce et vous trouvez un " + name + " !\n" + this);
        if (player.getType().equalsIgnoreCase(usableBy)) {
            System.out.println("Voulez-vous vous en équiper ?\n1 - Oui\n2 - Non");
            if (eventUser.nextInt() == 1) {
                player.setOffensive(this);
                System.out.println("Ca y est, c'est équipé");
            }
            else {
                System.out.println("Ok ça marche.");
            }
        } else {
            System.out.println("Désolé, tu sais pas manier ça, passe ton chemin");
        }
    }

    @Override
    public String toString() {
        return "\n----- FICHE DE L'ARME -----" +
                "\nNom : " + name +
                "\nType : " + getType() +
                "\nStat : " + value +
                "\n---------------------------";
    }
}