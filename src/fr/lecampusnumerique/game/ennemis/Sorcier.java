package fr.lecampusnumerique.game.ennemis;

public class Sorcier extends Ennemi {
    public Sorcier() {
        super("Karaba");
        setAttack(2);
        setLife(9);
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