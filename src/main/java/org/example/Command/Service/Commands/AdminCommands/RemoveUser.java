package org.example.Command.Service.Commands.AdminCommands;

import org.example.bd.WorkerBd;
import org.example.entity.Users.User;
import org.example.Util.Logger;
import org.example.Util.UtilInput;
import org.example.Command.AbstractCommand;

import java.io.Serializable;

public class RemoveUser extends AbstractCommand implements Serializable {
    public RemoveUser() {
        super("Удалить пользователя");
    }

    @Override
    public boolean execute() {
        System.out.println("Список пользователей:");
        for (User user : User.getUsers()) {
            System.out.println(user.getLogin());
        }

        System.out.println("Введите логин пользователя для удаления:");
        String login = UtilInput.getRequiredStringFromUser();

        User userToRemove = User.findUserByLogin(login);
        if (userToRemove != null) {
            WorkerBd.INSTANCE.removeUser(userToRemove);
            User.getUsers().remove(userToRemove);
            Logger.getInstance().log("Пользователь " + login + " был удален.");
            System.out.println("Пользователь " + login + " успешно удален.");
        } else {
            System.err.println("Пользователя с таким логином не существует.");
        }

        return true;
    }
}
