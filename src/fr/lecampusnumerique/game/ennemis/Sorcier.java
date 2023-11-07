package fr.lecampusnumerique.game.ennemis;
/**
 * Classe Sorcier héritant de la Classe Ennemi.
 */
public class Sorcier extends Ennemi {
    public Sorcier() {
        super("Karaba", 2, 9);
    }

    /**
     * Permet de récupérer le type
     * @return String "Sorcier"
     */
    @Override
    public String getType() {
        return "Sorcier";
    }
}