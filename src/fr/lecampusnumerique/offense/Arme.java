package fr.lecampusnumerique.offense;

public class Arme extends EquipementOffensif {
    public Arme(String pName){
        super(pName);
        setValue(4);
    }
    public Arme(){
        this("Bâton furieux"); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Arme";
    }
}