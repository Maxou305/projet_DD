package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.defense.EquipementDefensif;
import fr.lecampusnumerique.offense.EquipementOffensif;

public abstract class Personnage {
    private String name;
    private int life;
    private int strength;
    private EquipementOffensif offensive;
    private EquipementDefensif defensive;

    protected Personnage(String pName) {
        name = pName;
    }

    public void displayStats() {
        System.out.println(this);
    }

    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getType();
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public EquipementOffensif getOffensive() {
        return offensive;
    }

    public void setOffensive(EquipementOffensif offensive) {
        this.offensive = offensive;
    }

    public EquipementDefensif getDefensive() {
        return defensive;
    }

    public void setDefensive(EquipementDefensif defensive) {
        this.defensive = defensive;
    }

    @Override
    public String toString() {
        return "----- FICHE DU PERSO -----" +
                "\nNom : " + name +
                "\nClasse : " + getType() +
                "\nHP : " + life +
                "\nForce : " + strength +
                "\nOffensive : " + offensive +
                "\nDefensive : " + defensive +
                "\n--------------------------";
    }
}