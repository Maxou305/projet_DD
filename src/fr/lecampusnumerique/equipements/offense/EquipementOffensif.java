package fr.lecampusnumerique.equipements.offense;

import fr.lecampusnumerique.game.iCell;
import fr.lecampusnumerique.personnages.Personnage;
import fr.lecampusnumerique.main.iVisitorClasseEquipementOffensif;

import java.util.Scanner;

public abstract class EquipementOffensif implements iCell, iVisitorClasseEquipementOffensif {
    private final int value;
    private final String name;
    private String usableBy;

    protected EquipementOffensif(String pName, int pValue) {
        name = pName;
        value = pValue;
    }

    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------

    public abstract String getType();

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setUsableBy(String usableBy) {
        this.usableBy = usableBy;
    }

    public void interaction(Personnage pPlayer) {
        Scanner eventUser = new Scanner(System.in);
        System.out.println("Vous entrez dans la pièce et vous trouvez un " + name + " !\n" + this);
        if (pPlayer.getType().equalsIgnoreCase(usableBy)) {
            System.out.println("Voulez-vous vous en équiper ?\n1 - Oui\n2 - Non");
            if (eventUser.nextInt() == 1) {
                pPlayer.setOffensive(this);
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