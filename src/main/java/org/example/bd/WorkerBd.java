package org.example.bd;
import org.example.entity.Tournament;
import org.example.entity.Users.Player;
import org.example.entity.Users.User;

import java.sql.*;
public class WorkerBd {
    public static WorkerBd INSTANCE = new WorkerBd();
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private static final String CREATE_TOURNAMENT_TABLE =
            "CREATE TABLE IF NOT EXISTS tournament (" +
                    "id SERIAL PRIMARY KEY, " +
                    "date DATE NOT NULL, " +
                    "type_of_game VARCHAR(50) NOT NULL " +
                    ");";
    private static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "login VARCHAR(100) UNIQUE NOT NULL, " +
                    "role VARCHAR(50) NOT NULL" +
                    ");";

    private static final String CREATE_PLAYER_TOURNAMENT_REGISTRATION_TABLE =
            "CREATE TABLE IF NOT EXISTS player_tournament_registration (" +
                    "id SERIAL PRIMARY KEY, " +
                    "player_id INT NOT NULL, " +
                    "tournament_id INT NOT NULL, " +
                    "FOREIGN KEY (player_id) REFERENCES users(id), " +
                    "FOREIGN KEY (tournament_id) REFERENCES tournament(id)" +
                    ");";

    private WorkerBd() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                createTournamentTable(connection);
                createUserTable(connection);
                createPlayerTournamentRegistrationTable(connection);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка подключения: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void createTournamentTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TOURNAMENT_TABLE);
        }
    }

    private void createUserTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_USER_TABLE);
        }
    }

    public void addUser(User user) {
        String ADD_USER_QUERY = "INSERT INTO users (name, login, role) VALUES (?, ?, ?) RETURNING id;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_QUERY)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getRole().getName());

            ResultSet generatedKeys = preparedStatement.executeQuery();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTournament(Tournament tournament) {
        String ADD_TOURNAMENT_QUERY = "INSERT INTO tournament (date, type_of_game) VALUES (?, ?) RETURNING id;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TOURNAMENT_QUERY)) {

            preparedStatement.setDate(1, java.sql.Date.valueOf(tournament.data));
            preparedStatement.setString(2, tournament.typeOfGame.toString());

            ResultSet generatedKeys = preparedStatement.executeQuery();
            if (generatedKeys.next()) {
                tournament.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPlayerTournamentRegistration(Player player, Tournament tournament) {
        String ADD_REGISTRATION_QUERY = "INSERT INTO player_tournament_registration (player_id, tournament_id) VALUES (?, ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_REGISTRATION_QUERY)) {

            preparedStatement.setInt(1, player.getId());
            preparedStatement.setInt(2, tournament.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void createPlayerTournamentRegistrationTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_PLAYER_TOURNAMENT_REGISTRATION_TABLE);
        }
    }

    public void removeUser(User user) {
        String REMOVE_REGISTRATIONS_QUERY = "DELETE FROM player_tournament_registration WHERE player_login = ?;";
        String REMOVE_USER_QUERY = "DELETE FROM users WHERE login = ?;";

        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_REGISTRATIONS_QUERY)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.executeUpdate();
            }

            // Удаляем пользователя
            try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_QUERY)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removePlayerFromTournament(Player player, Tournament tournament) {
        String REMOVE_REGISTRATION_QUERY =
                "DELETE FROM player_tournament_registration WHERE player_login = ? AND tournament_id = ?;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_REGISTRATION_QUERY)) {

            preparedStatement.setString(1, player.getLogin());
            preparedStatement.setInt(2, tournament.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}