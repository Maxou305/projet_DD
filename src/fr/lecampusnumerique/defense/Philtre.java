package fr.lecampusnumerique.defense;

public class Philtre extends EquipementDefensif {
    public Philtre(String pName) {
        super(pName);
    }

    public Philtre() {
        this("Miroir miroir"); // this() permet d'appeler le constructeur de base !
        setValue(0);
    }

    @Override
    public String getType() {
        return "Philtre";
    }
}