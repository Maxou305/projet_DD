package fr.lecampusnumerique.game.ennemis;

public class Sorcier extends Ennemi {
    public Sorcier() {
        super("Karaba");
        setLife(2);
        setAttack(9);
    }

    @Override
    public String getType() {
        return "Sorcier";
    }
}