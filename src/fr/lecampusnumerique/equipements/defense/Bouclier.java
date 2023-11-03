package fr.lecampusnumerique.equipements.defense;

public class Bouclier extends EquipementDefensif {

    public Bouclier() {
        super("Bouclier arverne", 0);
    }

    @Override
    public String getType() {
        return "Bouclier";
    }
}