package fr.lecampusnumerique.offense;

import fr.lecampusnumerique.game.Cell;
import fr.lecampusnumerique.personnages.Personnage;

public abstract class EquipementOffensif implements Cell {
    private int value;
    private String name;

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

    public void interaction(Personnage player) {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return  "----- FICHE DE L'ARME -----"+
                "\nNom : " + name +
                "\nType : " + getType() +
                "\nStat : " + value +
                "\n---------------------------";
    }
}