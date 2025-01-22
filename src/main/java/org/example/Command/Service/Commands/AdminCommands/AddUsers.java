package org.example.Command.Service.Commands.AdminCommands;
import org.example.entity.Users.UserService.AdminService;

import org.example.entity.Users.UserService.PlayerService;
import org.example.Util.UtilInput;
import org.example.Command.AbstractCommand;


public class AddUsers extends AbstractCommand {

    public AddUsers() {
        super("Добавление пользователя");
    }

    @Override
    public boolean execute() {
        System.out.println("Введите соответствующую цифру пользователя, которого хотите добавить: ");
        System.out.println("1. Aдминистратор");
        System.out.println("2. Игрок");
        int resultGenre = UtilInput.getRequiredIntFromUser(1, 2);
        switch (resultGenre) {
            case 1:
                AdminService.createAdmin();
                break;
            case 2:
                PlayerService.createPlayer();
                break;

        }
        
        return true;
    }
}





