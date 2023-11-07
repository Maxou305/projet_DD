package fr.lecampusnumerique.main;

import fr.lecampusnumerique.personnages.DataHero;
import fr.lecampusnumerique.personnages.Personnage;

import java.sql.*;  // pour les programmes standards de JDBC
import java.util.ArrayList;

public class ConnexionBDD {
    private Connection conMyDB;

    /**
     * Méthode permettant la connexion à la BDD.
     * @return true ou false selon si la BDD est connectée ou non
     */
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
     * Permet de récupérer tous les héros de la BDD sous forme d'ArrayList.
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

    /**
     * Permet de renvoyer un héros en fonction d'un ID choisi par l'utilisateur.
     * @param pUserChoice choix du héros sous forme d'int
     * @return ResultSet correspondant à un héros
     * @throws SQLException erreur renvoyée s'il y a eu un problème dans la requête SQL
     */
    public ResultSet getHeroByID(int pUserChoice) throws SQLException {
        String sql = "SELECT * FROM hero WHERE id = ?";
        ResultSet rs = null;
        try {
            PreparedStatement stmt = conMyDB.prepareStatement(sql);
            stmt.setInt(1, pUserChoice);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.getMessage();
        }
        return rs;
    }

    /**
     * Méthode permettant la sauvegarde du joueur dans la BDD
     * @param pPlayer joueur à sauvegarder
     * @throws SQLException erreur renvoyée si la requête SQL n'est pas passée
     */
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

    /**
     * Méthode permettant de modifier dans la BDD le niveau de vie du joueur (non-utilisée pour le moment).
     * @param pPlayer joueur
     */
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