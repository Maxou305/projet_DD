package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.defense.Bouclier;
import fr.lecampusnumerique.offense.guerrier.Arme;

public class Guerrier extends Personnage {
    public Guerrier(String pPlayerName) {
        super(pPlayerName);
        this.setLife(5);
        this.setStrength(5);
        this.setHpMax(10);
        this.setDefensive(new Bouclier());
        this.setOffensive(new Arme());
    }

    @Override
    public String getType() {
        return "Guerrier";
    }


}