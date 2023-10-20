package fr.lecampusnumerique.defense;

public class Bouclier extends EquipementDefensif {
    public Bouclier(String pName) {
        super(pName);
        setValue(4);
    }
    public Bouclier() {
        this("Bouclier arverne"); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Bouclier";
    }
}
