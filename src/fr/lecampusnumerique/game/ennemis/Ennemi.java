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
    public void interaction(Personnage pPlayer) {
        System.out.println("Alerte ! Vous avez rencontré un " + name + this);
        while (life >= 0 && pPlayer.getLife() >= 0) {
            pPlayer.attack(this);
            if (life > 0) pPlayer.damaged(this);
        }
        if (life < 0) {
            System.out.println("Bravo tu as éliminé l'ennemi ! Il te reste " + pPlayer.getLife() + " HP");
            return;
        }
        if (pPlayer.getLife() < 0) System.out.println("Déso t'es mort...");
    }

    @Override
    public String toString() {
        return "\n----- FICHE DE L'ENNEMI ----" +
                "\nNom : " + name +
                "\nLife : " + life +
                "\nAttaque : " + attack +
                "\n--------------------------";

    }
}