package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.equipements.defense.Bouclier;
import fr.lecampusnumerique.equipements.offense.guerrier.Arme;

/**
 * Classe Guerrier héritant de Personnage.
 */
public class Guerrier extends Personnage {
    /**
     * Constructeur de Guerrier appelant le super constructeur de Personnage.
     *
     * @param pName nom du joueur
     */
    public Guerrier(String pName) {
        super(pName, 5, 5, 10, new Arme(), new Bouclier());
    }

    @Override
    public String getType() {
        return "guerrier";
    }
}