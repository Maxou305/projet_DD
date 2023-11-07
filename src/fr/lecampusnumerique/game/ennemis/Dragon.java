package fr.lecampusnumerique.game.ennemis;

/**
 * Classe Dragon héritant de la Classe Ennemi.
 */
public class Dragon extends Ennemi {
    public Dragon(){
        super("Smaug", 4, 15);
    }

    /**
     * Permet de récupérer le type.
     * @return String "Dragon"
     */
    @Override
    public String getType() {
        return "Dragon";
    }
}