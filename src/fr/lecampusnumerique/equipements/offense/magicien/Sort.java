package fr.lecampusnumerique.equipements.offense.magicien;

import fr.lecampusnumerique.equipements.offense.EquipementOffensif;

/**
 * Class Sort, extension de EquipementOffensif. Utilisable uniquement par le Magicien.
 */
public class Sort extends EquipementOffensif {
    /**
     * Constructeur de Sort prenant 2 paramètres.
     * @param pName nom de l'arme
     * @param pValue valeur d'attaque de l'arme
     */
    public Sort(String pName, int pValue) {
        super(pName, pValue);
        setUsableBy("magicien");
    }

    /**
     * Constructeur de Sort appelant le constrcuteur de EquipementOffensif.
     */
    public Sort() {
        this("Popo magique", 0); // this() permet d'appeler le constructeur de base !
    }

    /**
     * Méthode permettant d'obtenir le type de l'arme.
     * @return type "Sort" de l'arme
     */
    @Override
    public String getType() {
        return "Sort";
    }

}