package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.defense.Philtre;

public class Magicien extends Personnage {
    public Magicien(String pName){
        super(pName);
        this.setLife(3);
        this.setStrength(8);
        this.setDefensive(new Philtre());
    }
    @Override
    public String getType() {
        return "Magicien";
    }
}