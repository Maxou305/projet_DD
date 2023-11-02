package fr.lecampusnumerique.main;

import fr.lecampusnumerique.personnages.Personnage;

import java.sql.*;  // pour les programmes standards de JDBC

public class ConnexionBDD {
    private Connection conMyDB;

    public boolean Connect() {
        try {
            // AU CAMPUS C'EST CA ------>
            conMyDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjons_dragons", "root", "root");
            // A LA MAISON C'EST CA ------>
//            conMyDB = DriverManager.getConnection("jdbc:mysql://localhost:8889/donjons_dragons", "root", "root");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Permet de récupérer tous les héros de la BDD.
     *
     * @throws SQLException renvoie une erreur si la requête SQL n'est pas passée.
     */
    public void displayHeroes() throws SQLException {
        Statement stmt = conMyDB.createStatement(); // objet Statement permettant d'initialiser un statement pour la future requête SQL.
        ResultSet rs = stmt.executeQuery("SELECT * FROM Hero"); // objet ResultSet tableau dont les colonnes sont celles qui ont été extraites par notre requête SQL, et dont les lignes sont les résultats de cette requête.
        while (rs.next()) { // .next() pour les ResultSet permet de passer à la ligne suivante et donc d'itérer la table récupérée.
            System.out.println("id : " + rs.getInt("id")); //.getInt() permet de récupérer un int en lui donnant le label de la colonne.
            System.out.println("Type : " + rs.getString("type"));//.getString() permet de récupérer un String en lui donnant le label de la colonne.
            System.out.println("Name : " + rs.getString("name"));
            System.out.println("Life : " + rs.getInt("life"));
            System.out.println("Strength : " + rs.getInt("strength"));
            System.out.println("Offensive : " + rs.getString("offensive"));
            System.out.println("Defensive : " + rs.getString("defensive"));
        }
    }

    public void displayHeroesID() throws SQLException {
        Statement stmt = conMyDB.createStatement(); // objet Statement permettant d'initialiser un statement pour la future requête SQL.
        ResultSet rs = stmt.executeQuery("SELECT * FROM Hero");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
        }
    }

    public ResultSet getHeroByID(int pUserChoice) throws SQLException {
        int userChoice = pUserChoice;
        String sql = "SELECT * FROM hero WHERE id = ?";
        ResultSet rs = null;
        try {
            PreparedStatement stmt = conMyDB.prepareStatement(sql);
            stmt.setInt(1, userChoice);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.getMessage();
        }
        return rs;
    }

    public void savePlayerInBDD(Personnage pPlayer) throws SQLException {
        PreparedStatement stmt = conMyDB.prepareStatement("UPDATE hero SET name = ?, life = ?, type = ?, strength = ?, offensive = ?, defensive = ? WHERE id = ?");
        stmt.setString(1, pPlayer.getName());
        stmt.setInt(2, pPlayer.getLife());
        stmt.setString(3, pPlayer.getType());
        stmt.setInt(4, pPlayer.getStrength());
        stmt.setString(5, pPlayer.getOffensive().getName());
        stmt.setString(6, pPlayer.getDefensive().getName());
        stmt.setInt(7, pPlayer.getId());
        stmt.executeUpdate();
    }

    public void changeLifePoints(Personnage pPlayer) {
        try {
            PreparedStatement stmt = conMyDB.prepareStatement("UPDATE hero SET life = ? WHERE id = ?");
            stmt.setInt(1, pPlayer.getLife());
            stmt.setInt(2, pPlayer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problème dans l'update de la vie : " + e.getMessage());
        }
    }
}