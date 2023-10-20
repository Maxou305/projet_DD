package fr.lecampusnumerique.offense.guerrier;

import fr.lecampusnumerique.game.Cell;
import fr.lecampusnumerique.offense.EquipementOffensif;

public abstract class Arme extends EquipementOffensif implements Cell {
    public Arme(String pName) {
        super(pName);
    }

    public Arme() {
        this("Bâton furieux"); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Arme";
    }

    @Override
    public void interact() {
        System.out.println("Oh, un salami");
    }
}