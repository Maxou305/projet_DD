package fr.lecampusnumerique.equipements.defense;

public abstract class EquipementDefensif {
    private int value;
    private String name;

    protected EquipementDefensif(String pName) {
        name = pName;
    }

    public abstract String getType();


    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return  "\n----- FICHE DU SHIELD -----"+
                "\nNom : " + name +
                "\nType : " + getType() +
                "\nStat : " + value +
                "\n---------------------------";
    }
}