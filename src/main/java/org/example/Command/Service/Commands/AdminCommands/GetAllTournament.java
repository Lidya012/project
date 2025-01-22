package org.example.Command.Service.Commands.AdminCommands;

import org.example.entity.Tournament;
import org.example.entity.TournamentMenager;
import org.example.Command.AbstractCommand;
import java.util.List;

public class GetAllTournament extends AbstractCommand {
    public GetAllTournament() {
        super("Показать все турниры");
    }

    @Override
    public boolean execute() {
        List<Tournament> tournaments = TournamentMenager.getInstance().getTournaments();
        if (tournaments.isEmpty()) {
            System.out.println("Нет доступных турниров.");
            return true;
        }
        System.out.println("Список всех турниров:");
        for (int i = 0; i < tournaments.size(); i++) {
            System.out.println(String.format("%d - %s", i + 1, tournaments.get(i).toString()));
        }

        return true;
    }
}
