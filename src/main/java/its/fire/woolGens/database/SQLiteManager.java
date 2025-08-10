package its.fire.woolGens.database;

import java.sql.*;
import java.util.UUID;

public class SQLiteManager {

    private Connection connection;

    public SQLiteManager(String databasePath) {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
            System.out.println("Datenbankverbindung erfolgreich hergestellt!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Herstellen der Datenbankverbindung!");
        }
    }

    public void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS players (uuid VARCHAR(36) PRIMARY KEY, money DOUBLE);";

        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Datenbank-Tabellen erfolgreich erstellt oder gefunden!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Erstellen der Datenbank-Tabellen!");
        }
    }

    public boolean hasPlayer(UUID uuid) {
        String sql = "SELECT uuid FROM players WHERE uuid = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid.toString());
            ResultSet rs = statement.executeQuery();
            return rs.next(); // Gibt true zurück, wenn ein Eintrag gefunden wird
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createPlayer(UUID uuid, double money) {
        String sql = "INSERT INTO players (uuid, money) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid.toString());
            statement.setDouble(2, money);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getMoney(UUID uuid) {
        String sql = "SELECT money FROM players WHERE uuid = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid.toString());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getDouble("money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; //
    }

    public void setMoney(UUID uuid, double money) {
        String sql = "UPDATE players SET money = ? WHERE uuid = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, money);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}