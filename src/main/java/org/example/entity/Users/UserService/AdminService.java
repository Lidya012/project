package org.example.entity.Users.UserService;

import org.example.bd.WorkerBd;
import org.example.entity.Users.Admin;
import org.example.entity.Users.User;
import org.example.Util.Logger;
import org.example.Util.UtilInput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements Serializable {
    private static final long serialVersionUID = 1L;
    private static List<Admin> admins = new ArrayList<>();

    public static boolean createAdmin() {
        System.out.println("Введите имя администратора ");
        String name = UtilInput.getRequiredStringFromUser();
        System.out.println("Введите логин");
        String login = UtilInput.getRequiredStringFromUser();
        if (User.findUserByLogin(login) == null) {
            Admin admin = new Admin(name, login);
            Logger.getInstance().log("Добавлен администратор " + name);
            admins.add(admin);
            WorkerBd.INSTANCE.addUser(admin);
        } else {
            System.out.println("Пользователь уже зарегистрирован");

        }      return true;

    }
    public static List<Admin> getAdmins() {
        return admins;
    }

    public static void setAdmins(List<Admin> admins) {
        AdminService.admins = admins;
    }

    }

