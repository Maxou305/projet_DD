package fr.lecampusnumerique.game.potions;

import fr.lecampusnumerique.game.Cell;
import fr.lecampusnumerique.personnages.Personnage;

public abstract class Potion implements Cell {
    String name;
    int heal;

    public Potion(String pName, int pHeal) {
        name = pName;
        heal = pHeal;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    @Override
    public void interaction(Personnage player) {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return  "----- FICHE DE LA POTION -----" +
                "\nNom : " + name +
                "\nHeal : " + heal +
                "\n----------------------------";
    }
}