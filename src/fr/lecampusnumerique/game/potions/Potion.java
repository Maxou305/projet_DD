package fr.lecampusnumerique.game.potions;

import fr.lecampusnumerique.game.iCell;
import fr.lecampusnumerique.personnages.Personnage;

/**
 * Classe abstraite Potion dont héritent PopoMini et PopoBig. Implémente l'interface iCell.
 */
public abstract class Potion implements iCell {
    String name;
    int heal;

    /**
     * Constructeur de Potion prenant 2 paramètres.
     * @param pName nom de la potion
     * @param pHeal valeur de soin de la Potion
     */
    public Potion(String pName, int pHeal) {
        name = pName;
        heal = pHeal;
    }

    /**
     * Méthode permettant l'interaction avec le joueur (soin).
     * @param pPlayer joueur
     */
    @Override
    public void interaction(Personnage pPlayer) {
        pPlayer.heal(heal);
        if (pPlayer.getLife() >= pPlayer.getHpMax()) {
            pPlayer.setLife(pPlayer.getHpMax());
        }
        System.out.println("Vous avez trouvé une potion ! : \n" + this + "\nVotre vie est maintenant à " + pPlayer.getLife());
    }

    @Override
    public String toString() {
        return "----- FICHE DE LA POTION -----" +
                "\nNom : " + name +
                "\nHeal : " + heal +
                "\n----------------------------";
    }
}