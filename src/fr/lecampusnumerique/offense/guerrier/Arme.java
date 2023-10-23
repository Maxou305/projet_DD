package fr.lecampusnumerique.offense.guerrier;

import fr.lecampusnumerique.game.Cell;
import fr.lecampusnumerique.offense.EquipementOffensif;

public class Arme extends EquipementOffensif implements Cell {
    public Arme(String pName) {
        super(pName);
    }

    public Arme() {
        this("Bâton furieux"); setValue(0); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Arme";
    }
}