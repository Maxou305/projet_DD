package fr.lecampusnumerique.game.ennemis;

public class Gobelin extends Ennemi {
    public Gobelin() {
        super("Gripsec");
        setAttack(1);
        setLife(6);
    }

    @Override
    public String getType() {
        return "Gobelin";
    }
}