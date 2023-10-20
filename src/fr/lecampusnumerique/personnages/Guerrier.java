package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.defense.Bouclier;
import fr.lecampusnumerique.offense.Arme;

public class Guerrier extends Personnage {
    public Guerrier(String pPlayerName) {
        super(pPlayerName);
        this.setLife(10);
        this.setStrength(10);
        this.setOffensive(new Arme());
        this.setDefensive(new Bouclier());
    }

    @Override
    public String getType() {
        return "Guerrier";
    }


}