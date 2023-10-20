package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.defense.Philtre;
import fr.lecampusnumerique.offense.Sort;

public class Magicien extends Personnage {
    public Magicien(String pName){
        super(pName);
        this.setLife(6);
        this.setStrength(15);
        this.setOffensive(new Sort());
        this.setDefensive(new Philtre());
    }
    @Override
    public String getType() {
        return "Magicien";
    }
}