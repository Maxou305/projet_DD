package fr.lecampusnumerique.game.ennemis;

public class Sorcier extends Ennemi {
    public Sorcier() {
        super("Karaba");
        setAttack(2);
        setLife(9);
    }

    @Override
    public String getType() {
        return "Sorcier";
    }
}