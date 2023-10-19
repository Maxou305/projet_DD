package fr.lecampusnumerique.defense;

public abstract class EquipementDefensif {
    private String defensiveType;
    private int value;
    private String name;

    @Override
    public String toString() {
        return "-----" +
                "\nNom : " + name +
                "\nType : " + defensiveType +
                "\nValeur : " + value;
    }

    // -------------------------------------- GETTERS & SETTERS--------------------------------------

    public String getDefensiveType() {
        return defensiveType;
    }

    public void setDefensiveType(String defensiveType) {
        this.defensiveType = defensiveType;
    }

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
