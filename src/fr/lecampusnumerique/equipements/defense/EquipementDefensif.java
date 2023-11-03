package fr.lecampusnumerique.equipements.defense;

public abstract class EquipementDefensif {
    private int value;
    private final String name;

    protected EquipementDefensif(String pName, int pValue) {
        name = pName;
        value = pValue;
    }

    public abstract String getType();

    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "\n----- FICHE DU SHIELD -----" +
                "\nNom : " + name +
                "\nType : " + getType() +
                "\nStat : " + value +
                "\n---------------------------";
    }
}