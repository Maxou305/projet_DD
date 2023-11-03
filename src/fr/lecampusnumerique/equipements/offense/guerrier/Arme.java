package fr.lecampusnumerique.equipements.offense.guerrier;

import fr.lecampusnumerique.equipements.offense.EquipementOffensif;

public class Arme extends EquipementOffensif {
    public Arme(String pName, int pValue) {
        super(pName, pValue);
        setUsableBy("guerrier");
    }

    public Arme() {
        this("Bâton furieux", 0); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Arme";
    }
}