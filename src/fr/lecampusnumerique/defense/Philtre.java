package fr.lecampusnumerique.defense;

public class Philtre extends EquipementDefensif {
    public Philtre(String pName) {
        super(pName);
        setValue(4);
    }

    public Philtre() {
        this("Miroir miroir"); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Philtre";
    }
}