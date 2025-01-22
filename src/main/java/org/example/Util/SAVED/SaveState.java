package org.example.Util.SAVED;

import org.example.entity.TournamentMenager;
import org.example.entity.Users.UserService.AdminService;

import org.example.entity.Users.UserService.PlayerService;
import org.example.entity.Users.User;
import org.example.Util.Logger;

import java.io.*;

public class SaveState {
    public static void saveState() {
        try (FileOutputStream fos = new FileOutputStream("prog.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // Сохраняем состояние всех необходимых объектов
            oos.writeObject(TournamentMenager.getInstance().getTournaments());
            oos.writeObject(AdminService.getAdmins());
            oos.writeObject(PlayerService.getPlayers());
            oos.writeObject(User.getUsers());
            Logger.getInstance().log("Состояние успешно сохранено.");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении состояния: " + e.getMessage());
        }
    }
}
