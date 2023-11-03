package fr.lecampusnumerique.main;

import fr.lecampusnumerique.personnages.Personnage;

public interface iVisitorClasseEquipementOffensif {
    void accept(Personnage pPersonnage);
}