package fr.lecampusnumerique.offense;

public class Sort extends EquipementOffensif {
    public Sort(String pName) {
        super(pName);
        setValue(4);
    }
    public Sort(){
        this("Popo magique"); // this() permet d'appeler le constructeur de base !
    }

    @Override
    public String getType() {
        return "Sort";
    }
}