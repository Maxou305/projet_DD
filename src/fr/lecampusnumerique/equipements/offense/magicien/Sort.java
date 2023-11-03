package fr.lecampusnumerique.equipements.offense.magicien;

import fr.lecampusnumerique.equipements.offense.EquipementOffensif;

public class Sort extends EquipementOffensif {
    public Sort(String pName, int pValue) {
        super(pName, pValue);
        setUsableBy("magicien");
    }
    public Sort(){
        this("Popo magique", 0); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Sort";
    }
}