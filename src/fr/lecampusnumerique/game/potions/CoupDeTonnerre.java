package fr.lecampusnumerique.game.potions;

import fr.lecampusnumerique.personnages.Personnage;

/**
 * Classe PopoBig héritant de Potion
 */
public class CoupDeTonnerre extends Potion {
    /**
     * Constructeur de PopoMini faisant appel au super constructeur de Potion.
     */
    public CoupDeTonnerre() {
        super("BIG FAT POT", 5);
    }

    /**
     * Méthode permettant l'interaction avec le joueur (soin).
     *
     * @param pPlayer joueur
     */
    @Override
    public void interaction(Personnage pPlayer) {
        pPlayer.setBuff(true);
        pPlayer.setStrength(pPlayer.getStrength()*2);
        System.out.println("Wow incroyable puissance éternelle ! Ton attaque est doublée pendant un tour.");
    }
}