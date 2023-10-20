package fr.lecampusnumerique.game;

import fr.lecampusnumerique.game.Frame;

public class FrameVide implements Frame {

    @Override
    public void interact() {
        System.out.println("OK BRO");
    }
}
