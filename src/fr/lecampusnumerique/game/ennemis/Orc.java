package fr.lecampusnumerique.game.ennemis;

import fr.lecampusnumerique.personnages.Guerrier;
import fr.lecampusnumerique.personnages.Personnage;
import fr.lecampusnumerique.game.iCell;

/**
 * Classe Dragon héritant de la Classe Ennemi.
 */
public class Orc extends Ennemi implements iCell {
    public Orc() {
        super("Guénaël", 6, 10);
    }


    /**
     * Permet de récupérer le type.
     *
     * @return String "Orc"
     */
    @Override
    public String getType() {
        return "Orc";
    }

    @Override
    public void interaction(Personnage pPlayer) {
        if (pPlayer instanceof Guerrier g) {
            super.interaction(g);
        } else {
            System.out.println("Tu as de la chance Magicien, l'orc a peur du feu et ton célèbre sort 'Zippo' l'a fait fuir");
        }
    }
}