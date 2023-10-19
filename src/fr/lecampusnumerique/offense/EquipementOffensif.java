package fr.lecampusnumerique.offense;

public abstract class EquipementOffensif {
    private String offensiveType;
    private int value;
    private String name;


    // -------------------------------------- GETTERS & SETTERS--------------------------------------

    public String getOffensiveType() {
        return offensiveType;
    }

    public void setOffensiveType(String offensiveType) {
        this.offensiveType = offensiveType;
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


    @Override
    public String toString() {
        return "-----" +
                "\nNom : " + name +
                "\nType : " + offensiveType +
                "\nStat : " + value;
    }
}