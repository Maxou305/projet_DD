package fr.lecampusnumerique.game.ennemis;

public class Gobelin extends Ennemi {
    public Gobelin() {
        super("Gripsec", 1, 6);
    }

    @Override
    public String getType() {
        return "Gobelin";
    }
}