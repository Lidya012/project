package org.example.Command.Service.Commands.PlayerCommand;

import org.example.Util.Logger;
import org.example.Util.UtilInput;
import org.example.bd.WorkerBd;
import org.example.entity.Tournament;
import org.example.entity.TournamentMenager;
import org.example.entity.Users.Player;
import org.example.entity.Users.User;
import org.example.Command.AbstractCommand;

import java.util.List;

public class RegistrationForTheGame extends AbstractCommand {
    public RegistrationForTheGame() {
        super("Регистрация на чемпионат");
    }

    @Override
    public boolean execute() {
        List<Tournament> tournaments = TournamentMenager.getInstance().getTournaments();

        if (tournaments.isEmpty()) {
            System.out.println("Нет доступных турниров для регистрации.");
            return true;
        }

        System.out.println("Выберите турнир для регистрации:");
        for (int i = 0; i < tournaments.size(); i++) {
            System.out.println(String.format("%d - %s", i + 1, tournaments.get(i).toString()));
        }

        int choice = UtilInput.getRequiredIntFromUser(1, tournaments.size());
        Tournament selectedTournament = tournaments.get(choice - 1);

        Player currentPlayer = (Player) User.getCurrentUser();
        if (selectedTournament.hasPlayer(currentPlayer)) {
            System.out.println("Вы уже зарегистрированы на этот турнир: " + selectedTournament);
            return true;
        }

        selectedTournament.addPlayer(currentPlayer);
        WorkerBd.INSTANCE.addPlayerTournamentRegistration(currentPlayer, selectedTournament);
        System.out.println("Вы успешно зарегистрировались на турнир: " + selectedTournament);
        Logger.getInstance().log("Игорк" + currentPlayer.toString() + " зарегистирован на турнир "
                +selectedTournament.toString());

        return true;
    }
}







