package org.example.Util.SAVED;

import org.example.Util.Logger;
import org.example.entity.Tournament;
import org.example.entity.TournamentMenager;
import org.example.entity.Users.Admin;
import org.example.entity.Users.Player;
import org.example.entity.Users.User;
import org.example.entity.Users.UserService.AdminService;
import org.example.entity.Users.UserService.PlayerService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;


public class LoadState {

        public static void loadState() {
            try (FileInputStream fis = new FileInputStream("prog.bin");
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                // Загружаем состояние
                List<Tournament> tournaments = (List<Tournament>) ois.readObject();
                List<Admin> admins = (List<Admin>) ois.readObject();
                List<Player> players = (List<Player>) ois.readObject();
                List<User> users = (List<User>) ois.readObject();

                // Устанавливаем загруженные данные в соответствующие классы
                TournamentMenager.getInstance().setTournaments(tournaments);
                AdminService.setAdmins(admins);
                PlayerService.setPlayers(players);
                User.setUsers(users);

                Logger.getInstance().log("Состояние успешно загружено.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Ошибка при загрузке состояния: " + e.getMessage());
            }
        }
    }


