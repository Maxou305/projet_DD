package fr.lecampusnumerique.game.ennemis;

public class Dragon extends Ennemi {
    public Dragon(){
        super("Smaug");
        setLife(15);
        setAttack(4);
    }

    @Override
    public String getType() {
        return "Dragon";
    }
}