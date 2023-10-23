package fr.lecampusnumerique.game.potions;

import fr.lecampusnumerique.game.Cell;
import fr.lecampusnumerique.personnages.Personnage;

public abstract class Potion implements Cell {
    String name;
    int heal;

    public Potion(String pName, int pHeal) {
        name = pName;
        heal = pHeal;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    @Override
    public void interaction(Personnage pPlayer) {
        pPlayer.heal(heal);
        if (pPlayer.getLife() >= pPlayer.getHpMax()){
            pPlayer.setLife(10);
        }
        System.out.println("Vous avez trouvé une potion ! : \n"+this+"\nVotre vie est maintenant à "+ pPlayer.getLife());
    }

    @Override
    public String toString() {
        return  "----- FICHE DE LA POTION -----" +
                "\nNom : " + name +
                "\nHeal : " + heal +
                "\n----------------------------";
    }
}