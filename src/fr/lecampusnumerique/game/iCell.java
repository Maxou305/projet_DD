package fr.lecampusnumerique.game;

import fr.lecampusnumerique.personnages.Personnage;

/**
 * Interface permettant la gestion des interactions entre le joueur et les cases sur lequel il tombe
 */
public interface iCell {
    void interaction(Personnage player);
}