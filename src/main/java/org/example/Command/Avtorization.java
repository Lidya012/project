package org.example.Command;

import org.example.bd.WorkerBd;
import org.example.entity.Users.Admin;
import org.example.entity.Users.User;
import org.example.Util.UtilInput;
import org.example.Command.Service.CommandServiceforPlayer;
import org.example.Command.Service.CommandServisforAdmin;

public class Avtorization  {

    public static boolean avtorization() {
        if (findAdmin() == null) {
            Admin admin = new Admin("admin", "admin");
            WorkerBd.INSTANCE.addUser(admin);
        }
            System.out.println("ПРИВЕТ! Необходимо авторизоваться");
            System.out.println("Введите логин для авторизации:");
            String login = UtilInput.getRequiredStringFromUser();
            User user = User.findUserByLogin(login);

            if (user != null) {
                User.setCurrentUser(user);
                System.out.println("Добро пожаловать, " + user.getName() + "!");
                switch (user.getRole().toString()) {
                    case "Администратор":
                        CommandServisforAdmin.getInstance().printCommand();
                        break;
                    case "Игрок":
                        CommandServiceforPlayer.getInstance().printCommand();
                        break;
                    default:
                        System.err.println("Неизвестная роль пользователя.");
                        break;
                }
                return true;
            } else {
                System.err.print("Пользователь не найден. Пройдите регистрацию.\n");
                Registration.registration();
                return true;
            }
        }

        private static User findAdmin() {
            return User.findUserByLogin("admin");
        }
    }