package fr.lecampusnumerique.offense.guerrier;

import fr.lecampusnumerique.game.Frame;
import fr.lecampusnumerique.offense.EquipementOffensif;

public abstract class Arme extends EquipementOffensif implements Frame {
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