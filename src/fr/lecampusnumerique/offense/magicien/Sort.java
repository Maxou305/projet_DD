package fr.lecampusnumerique.offense.magicien;

import fr.lecampusnumerique.offense.EquipementOffensif;

public abstract class Sort extends EquipementOffensif {
    public Sort(String pName) {
        super(pName);
    }
    public Sort(){
        this("Popo magique"); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Sort";
    }
}