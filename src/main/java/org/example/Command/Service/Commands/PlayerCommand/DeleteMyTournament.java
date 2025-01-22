package org.example.Command.Service.Commands.PlayerCommand;

import org.example.Util.UtilInput;
import org.example.bd.WorkerBd;
import org.example.entity.Tournament;
import org.example.entity.Users.Player;
import org.example.entity.Users.User;
import org.example.Command.AbstractCommand;

import java.util.List;

public class DeleteMyTournament extends AbstractCommand {
    public DeleteMyTournament() {
        super("Удалить выбрвнный чемпионат");
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

        for (int i = 0; i < registeredTournaments.size(); i++) {
            System.out.println(String.format("%d - %s", i + 1, registeredTournaments.get(i).toString()));
        }

        int choice = UtilInput.getRequiredIntFromUser(1, registeredTournaments.size());
        Tournament selectedTournament = registeredTournaments.get(choice - 1);
        WorkerBd.INSTANCE.removePlayerFromTournament(currentPlayer, selectedTournament);
        selectedTournament.getPlayers().remove(currentPlayer);

        System.out.println("Вы успешно удалили себя из турнира: " + selectedTournament);

        return true;
    }
}

