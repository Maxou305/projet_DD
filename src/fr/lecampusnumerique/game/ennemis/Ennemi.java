package fr.lecampusnumerique.game.ennemis;

import fr.lecampusnumerique.game.Cell;
import fr.lecampusnumerique.personnages.Personnage;


public abstract class Ennemi implements Cell {
    private int life;
    private int attack;
    private String name;

    public Ennemi(String pName) {
        name = pName;
    }

    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public abstract String getType();

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    @Override
    public void interaction(Personnage player) {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "----- FICHE DE L'ENNEMI ----" +
                "\nNom : " + name +
                "\nLife : " + life +
                "\nAttaque : " + attack +
                "\n--------------------------";

    }
}