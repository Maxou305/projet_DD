package fr.lecampusnumerique.game.ennemis;

public class Gobelin extends Ennemi {
    public Gobelin() {
        super("Gripsec");
        setLife(1);
        setAttack(6);
    }

    @Override
    public String getType() {
        return "Gobelin";
    }
}