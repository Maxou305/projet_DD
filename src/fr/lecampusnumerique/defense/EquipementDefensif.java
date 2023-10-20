package fr.lecampusnumerique.defense;

public abstract class EquipementDefensif {
    private int value;
    private String name;

    protected EquipementDefensif(String pName) {
        name = pName;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "-----" +
                "\nNom : " + name +
                "\nType : " + getType() +
                "\nValeur : " + value;
    }

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
}