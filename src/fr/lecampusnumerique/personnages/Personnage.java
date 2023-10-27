package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.defense.EquipementDefensif;
import fr.lecampusnumerique.offense.EquipementOffensif;
import fr.lecampusnumerique.game.ennemis.Ennemi;

public abstract class Personnage {
    private int id;
    private String name;
    private int life;
    private int startLife;
    private int hpMax;
    private int strength;
    private int posPlayer;
    boolean exitFight = false;
    boolean winFight = false;
    private EquipementOffensif offensive;
    private EquipementDefensif defensive;

    protected Personnage(String pName) {
        name = pName;
    }

    // ----- METHODES ------------------------------------------------------------------------------------
    public void heal(int healing) {
        life += healing;
    }

    public void attack(Ennemi pEnnemi) {
        pEnnemi.setLife(pEnnemi.getLife() - (strength + offensive.getValue()));
    }

    public void damaged(Ennemi ennemi) {
        int damages = ennemi.getAttack() - defensive.getValue();
        life -= damages;
    }

    public void escape() {
        int result = 1 + (int) (Math.random() * ((6 - 1) + 1));
        posPlayer -= result;
        System.out.println("OK tu fuis, pas de bashing. Mais tu recules quand même de " + result + " cases. T'es maintenant sur la case " + posPlayer);
        exitFight = true;
    }

    public void moveBack(){
        posPlayer = 126 - posPlayer;
        System.out.println("STOOOOOOOOOOOOOOOOOP ! Tu vas trop loin !!!!! Tu recules à la " + posPlayer);
    }

    public void displayStats() {
        System.out.println(this);
    }

    // ----- GETTERS & SETTERS ------------------------------------------------------------------------------------

    public int getStartLife() {
        return startLife;
    }

    public void setStartLife(int startLife) {
        this.startLife = startLife;
    }

    public boolean isWinFight() {
        return winFight;
    }

    public void setWinFight(boolean winFight) {
        this.winFight = winFight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getType();

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
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

    public int getPosPlayer() {
        return posPlayer;
    }

    public void setPosPlayer(int position) {
        this.posPlayer = position;
    }

    public boolean isExitFight() {
        return exitFight;
    }

    public void setExitFight(boolean exitFight) {
        this.exitFight = exitFight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "----- FICHE DU PERSO -----" +
                "\nNom : " + name +
                "\nClasse : " + getType() +
                "\nHP : " + life +
                "\nForce : " + strength +
                "\n" + offensive +
                "\n" + defensive +
                "\n------------------------";
    }
}