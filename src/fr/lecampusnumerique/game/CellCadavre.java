package fr.lecampusnumerique.game;

import fr.lecampusnumerique.personnages.Personnage;

/**
 * Classe permettant la création d'objets CellCadavres à la place de la case Ennemi après la victoire du joueur.
 */
public class CellCadavre implements iCell {
    @Override
    public void interaction(Personnage player) {
        System.out.println("La pièce est vide et un cadavre git sur le sol. On dirait que t'as déjà fait le ménage... Bravo champion");
    }
}