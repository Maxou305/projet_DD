package fr.lecampusnumerique.exceptions;

public class MauvaisChoixUtilisateur extends Exception {
    public MauvaisChoixUtilisateur(){
        System.out.println("Cette option n'existe pas, réessaie !");
    }
}
