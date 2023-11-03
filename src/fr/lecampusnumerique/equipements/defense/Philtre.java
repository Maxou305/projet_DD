package fr.lecampusnumerique.equipements.defense;

public class Philtre extends EquipementDefensif {

    public Philtre() {
        super("Miroir miroir", 0); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Philtre";
    }
}