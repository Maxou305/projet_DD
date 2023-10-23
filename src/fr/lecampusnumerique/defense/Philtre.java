package fr.lecampusnumerique.defense;

public class Philtre extends EquipementDefensif {
    public Philtre(String pName) {
        super(pName);
    }

    public Philtre() {
        this("Miroir miroir"); setValue(0);; // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Philtre";
    }
}