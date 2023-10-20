package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.defense.Bouclier;

public class Guerrier extends Personnage {
    public Guerrier(String pPlayerName) {
        super(pPlayerName);
        this.setLife(5);
        this.setStrength(5);
        this.setDefensive(new Bouclier());
    }

    @Override
    public String getType() {
        return "Guerrier";
    }


}