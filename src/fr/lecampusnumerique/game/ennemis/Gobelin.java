package fr.lecampusnumerique.game.ennemis;

/**
 * Classe Gobelin héritant de la Classe Ennemi.
 */
public class Gobelin extends Ennemi {
    public Gobelin() {
        super("Gripsec", 1, 6);
    }

    /**
     * Permet de récupérer le type.
     * @return String "Gobelin"
     */
    @Override
    public String getType() {
        return "Gobelin";
    }
}