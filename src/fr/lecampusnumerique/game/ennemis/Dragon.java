package fr.lecampusnumerique.game.ennemis;

public class Dragon extends Ennemi {
    public Dragon(){
        super("Smaug", 4, 15);
    }

    @Override
    public String getType() {
        return "Dragon";
    }
}