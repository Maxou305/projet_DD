package fr.lecampusnumerique.game;

import fr.lecampusnumerique.personnages.*;

public class CellVide implements Cell {

    @Override
    public void interaction(Personnage player) {
        System.out.println("OK BRO");
    }
}
