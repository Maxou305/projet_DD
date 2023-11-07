package fr.lecampusnumerique.game.ennemis;

import fr.lecampusnumerique.personnages.Magicien;
import fr.lecampusnumerique.personnages.Personnage;

/**
 * Classe Dragon héritant de la Classe Ennemi.
 */
public class MauvaisEsprit extends Ennemi {
    public MauvaisEsprit() {
        super("Casper", 5, 15);
    }

    /**
     * Permet de récupérer le type.
     *
     * @return String "Orc"
     */
    @Override
    public String getType() {
        return "MauvaisEsrpit";
    }

    @Override
    public void interaction(Personnage pPlayer) {
        if (pPlayer instanceof Magicien m) {
            super.interaction(m);
        } else {
            System.out.println("Tu as de la chance Guerrier, le fantôme a peur de tes gros bras musclés");
        }
    }
}