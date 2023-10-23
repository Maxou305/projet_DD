package fr.lecampusnumerique.game.ennemis;

public class Dragon extends Ennemi {
    public Dragon(){
        super("Smaug");
        setAttack(4);
        setLife(15);
    }

    @Override
    public String getType() {
        return "Dragon";
    }
}