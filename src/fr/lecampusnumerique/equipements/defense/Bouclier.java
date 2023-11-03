package fr.lecampusnumerique.equipements.defense;

public class Bouclier extends EquipementDefensif {
    public Bouclier(String pName) {
        super(pName);
    }

    public Bouclier() {
        this("Bouclier arverne");
        setValue(0); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Bouclier";
    }
}