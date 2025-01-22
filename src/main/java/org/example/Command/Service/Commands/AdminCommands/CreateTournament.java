package org.example.Command.Service.Commands.AdminCommands;

import org.example.entity.TournamentMenager;
import org.example.Command.AbstractCommand;

public class CreateTournament extends AbstractCommand {
    public CreateTournament() {
        super("Создать турнир");
    }

    @Override
    public boolean execute() {
        TournamentMenager.create();
        return true;
    }
}
