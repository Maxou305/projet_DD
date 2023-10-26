package fr.lecampusnumerique.main;

import java.sql.*;  // pour les programmes standards de JDBC


public class ConnexionBDD {
    Connection conMyDB;

    public ConnexionBDD() {
        try {
            conMyDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjons_dragons", "root", "root");
        } catch (SQLException e) {
            System.out.println("Erreur : problème de driver !");
            System.exit(1);
        }
    }

    /**
     * Permet de récupérer tous les héros de la BDD.
     * @throws SQLException renvoie une erreur si la requête SQL n'est pas passée.
     */
    public void getHeroes() throws SQLException {
        Statement stmt = conMyDB.createStatement(); // objet Statement permettant d'initialiser un statement pour la future requête SQL.
        ResultSet rs = stmt.executeQuery("SELECT * FROM Hero"); // objet ResultSet tableau dont les colonnes sont celles qui ont été extraites par notre requête SQL, et dont les lignes sont les résultats de cette requête.
        while(rs.next()) { // .next() pour les ResultSet permet de passer à la ligne suivante et donc d'itérer la table récupérée.
            System.out.println("id : " + rs.getInt("id")); //.getInt() permet de récupérer un int en lui donnant le label de la colonne.
            System.out.println("type : " + rs.getString("type"));//.getString() permet de récupérer un String en lui donnant le label de la colonne.
            System.out.println("name : " + rs.getString("name"));
            System.out.println("life : " + rs.getInt("life"));
            System.out.println("strength : " + rs.getInt("strength"));
            System.out.println("offensive : " + rs.getString("offensive"));
            System.out.println("defensive : " + rs.getString("defensive"));

        }
    }

    public void createHero(){}
    public void editHero(){}
    public void changeLifePoints(){}
}