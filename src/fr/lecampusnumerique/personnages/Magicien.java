package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.equipements.defense.Philtre;
import fr.lecampusnumerique.equipements.offense.magicien.Sort;

public class Magicien extends Personnage {
    public Magicien(String pName){
        super(pName);
        this.setLife(3);
        this.setStrength(8);
        this.setHpMax(6);
        this.setDefensive(new Philtre());
        this.setOffensive(new Sort());
    }
    @Override
    public String getType() {
        return "magicien";
    }
}