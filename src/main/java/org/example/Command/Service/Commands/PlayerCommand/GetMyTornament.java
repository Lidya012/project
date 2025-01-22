package org.example.Command.Service.Commands.PlayerCommand;

import org.example.entity.Tournament;
import org.example.entity.Users.Player;
import org.example.entity.Users.User;
import org.example.Command.AbstractCommand;

import java.util.List;

public class GetMyTornament extends AbstractCommand {
    public GetMyTornament() {
        super("Просмотр выбранных чемпионатов");
    }

    @Override
    public boolean execute() {
        Player currentPlayer = (Player) User.getCurrentUser();
        List<Tournament> registeredTournaments = currentPlayer.getRegisteredTournaments();

        if (registeredTournaments.isEmpty()) {
            System.out.println("У вас нет зарегистрированных турниров.");
            return true;
        }

        System.out.println("Ваши зарегистрированные турниры:");
        for (Tournament tournament : registeredTournaments) {
            System.out.println(tournament.toString());
        }

        return true;
    }
    }