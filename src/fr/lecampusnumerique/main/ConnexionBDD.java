package fr.lecampusnumerique.main;

import fr.lecampusnumerique.personnages.DataHero;
import fr.lecampusnumerique.personnages.Personnage;

import javax.xml.crypto.Data;
import java.sql.*;  // pour les programmes standards de JDBC
import java.util.ArrayList;
import java.util.HashMap;

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
    public ArrayList<DataHero> getHeroesFromBDD() throws SQLException {
        Statement stmt = conMyDB.createStatement(); // objet Statement permettant d'initialiser un statement pour la future requête SQL.
        ResultSet rs = stmt.executeQuery("SELECT * FROM Hero"); // objet ResultSet tableau dont les colonnes sont celles qui ont été extraites par notre requête SQL, et dont les lignes sont les résultats de cette requête.
        ArrayList<DataHero> zob = new ArrayList<>();
        while (rs.next()) { // .next() pour les ResultSet permet de passer à la ligne suivante et donc d'itérer la table récupérée.
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String type = rs.getString("type");//.getString() permet de récupérer un String en lui donnant le label de la colonne.
            int life = rs.getInt("life");
            int strength = rs.getInt("strength");
            String offensive = rs.getString("offensive");
            String defensive = rs.getString("defensive");
            zob.add(new DataHero(id, name, type, life, strength, offensive, defensive));
        }
        return zob;
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
        PreparedStatement stmt = conMyDB.prepareStatement("INSERT INTO `hero` (`type`, `name`, `life`, `strength`, `offensive`, `defensive`) VALUES (?, ?, ?, ?, ?, ?)");
        stmt.setString(1, pPlayer.getType());
        stmt.setString(2, pPlayer.getName());
        stmt.setInt(3, pPlayer.getLife());
        stmt.setInt(4, pPlayer.getStrength());
        stmt.setString(5, pPlayer.getOffensive().getName());
        stmt.setString(6, pPlayer.getDefensive().getName());
        stmt.executeUpdate();
    }

    public void changeLifePoints(Personnage pPlayer) {
        try {
            PreparedStatement stmt = conMyDB.prepareStatement("UPDATE hero SET life = ? WHERE id = ?");
            stmt.setInt(1, pPlayer.getLife());
            stmt.setInt(2, pPlayer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}