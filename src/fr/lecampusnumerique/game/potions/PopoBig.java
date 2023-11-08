package fr.lecampusnumerique.game.potions;

import fr.lecampusnumerique.personnages.Personnage;

/**
 * Classe PopoBig héritant de Potion
 */
public class PopoBig extends Potion {
    /**
     * Constructeur de PopoMini faisant appel au super constructeur de Potion.
     */
    public PopoBig() {
        super("BIG FAT POT", 5);
    }

    /**
     * Méthode permettant l'interaction avec le joueur (soin).
     *
     * @param pPlayer joueur
     */
    @Override
    public void interaction(Personnage pPlayer) {
        pPlayer.heal(value);
        System.out.println("Vous avez trouvé une potion ! : \n" + this + "\nVotre vie est maintenant à " + pPlayer.getLife());
    }
}