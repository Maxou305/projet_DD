package fr.lecampusnumerique.personnages;

import fr.lecampusnumerique.equipements.defense.Philtre;
import fr.lecampusnumerique.equipements.offense.magicien.Sort;

/**
 * Classe Magicien héritant de Personnage.
 */
public class Magicien extends Personnage {
    /**
     * Constructeur de Guerrier appelant le super constructeur de Personnage.
     *
     * @param pName nom du joueur
     */
    public Magicien(String pName) {
        super(pName, 3, 8, 6, new Sort(), new Philtre());
    }

    @Override
    public String getType() {
        return "magicien";
    }
}