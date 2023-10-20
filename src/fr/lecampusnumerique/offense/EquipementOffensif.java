package fr.lecampusnumerique.offense;

public abstract class EquipementOffensif {
    private int value;
    private String name;

    protected EquipementOffensif(String pName) {
        name = pName;
    }


    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------

    public abstract String getType();

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
        return "-----" +
                "\nNom : " + name +
                "\nType : " + getType() +
                "\nStat : " + value;
    }
}