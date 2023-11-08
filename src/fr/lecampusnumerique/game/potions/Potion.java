package fr.lecampusnumerique.game.potions;

import fr.lecampusnumerique.game.iCell;
import fr.lecampusnumerique.personnages.Personnage;

/**
 * Classe abstraite Potion dont héritent PopoMini et PopoBig. Implémente l'interface iCell.
 */
public abstract class Potion implements iCell {
    String name;
    int value;

    /**
     * Constructeur de Potion prenant 2 paramètres.
     * @param pName nom de la potion
     * @param pValue valeur de soin de la Potion
     */
    public Potion(String pName, int pValue) {
        name = pName;
        value = pValue;
    }



    @Override
    public String toString() {
        return "----- FICHE DE LA POTION -----" +
                "\nNom : " + name +
                "\nValeur : " + value +
                "\n----------------------------";
    }
}