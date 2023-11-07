package fr.lecampusnumerique.game;

import fr.lecampusnumerique.personnages.*;

/**
 * Classe permettant d'ajouter des cases vides.
 */
public class CellVide implements iCell {

    @Override
    public void interaction(Personnage player) {
        System.out.println("C'est une salle vide, tu peux chill");
    }
}