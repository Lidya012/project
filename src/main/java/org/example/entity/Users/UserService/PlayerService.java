package org.example.entity.Users.UserService;

import org.example.bd.WorkerBd;
import org.example.entity.Tournament;
import org.example.entity.TournamentMenager;
import org.example.entity.Users.Player;

import org.example.entity.Users.User;
import org.example.Util.Logger;
import org.example.Util.UtilInput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerService implements Serializable {

    private static List<Player> players = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public static boolean createPlayer() {
        System.out.println("Введите имя игрока ");
        String name = UtilInput.getRequiredStringFromUser();
        System.out.println("Введите логин");
        String login = UtilInput.getRequiredStringFromUser();

        if (User.findUserByLogin(login) == null) {
            System.out.println("Введите фамилию игрока ");
            String surname = UtilInput.getRequiredStringFromUser();
            System.out.println("Введите возраст");
            Integer age = UtilInput.getRequiredIntFromUser();

            Player player = new Player(surname, name, login, age);
            Logger.getInstance().log("Добавлен игрок " + surname + " " + name);

            players.add(player);
            WorkerBd.INSTANCE.addUser(player);
            return true;
        } else {
            System.out.println("Пользователь уже зарегистрирован");
            return true;
        }

    }

    public static void setPlayers(List<Player> players) {
        PlayerService.players = players;
    }

    public static List<Player> getPlayers() {
        return players;
    }
}

