package fr.lecampusnumerique.game.potions;

import fr.lecampusnumerique.game.Cell;

public abstract class Potion implements Cell {
    int heal;

    public Potion(int pHeal) {
        heal = pHeal;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    @Override
    public void interact() {
        System.out.println("Glou glou");
    }
}
