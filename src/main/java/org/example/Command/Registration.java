package org.example.Command;

import org.example.entity.Users.UserService.AdminService;
import org.example.Util.UtilInput;
import org.example.entity.Users.UserService.PlayerService;

import static org.example.Command.Avtorization.avtorization;

public class Registration  {
  public static boolean registration() {
       System.out.println("Зарегистрируйся, выбери роль:");
       System.out.println("1. Aдминистратор");
       System.out.println("2. Участник");
       int result = UtilInput.getRequiredIntFromUser(1, 2);
       switch (result) {
           case 1:
               AdminService.createAdmin();
               break;
           case 2:
               PlayerService.createPlayer();
               break;
       }
       return avtorization();
   }
}
