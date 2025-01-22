package org.example.Command.Service;

import org.example.Command.AbstractCommand;

import org.example.Command.Service.Commands.PlayerCommand.DeleteMyTournament;
import org.example.Command.Service.Commands.PlayerCommand.GetMyTornament;
import org.example.Command.Service.Commands.PlayerCommand.RegistrationForTheGame;
import org.example.Command.Exit;


public class CommandServiceforPlayer extends CommandService<AbstractCommand> {
    protected CommandServiceforPlayer() {
        super();
    }

    @Override
    protected void initializeCommands() {
        commands.add(new RegistrationForTheGame());
        commands.add(new GetMyTornament());
        commands.add(new DeleteMyTournament());
        commands.add(new Exit());
    }

    public static CommandServiceforPlayer getInstance() {
        return (CommandServiceforPlayer) CommandService.getInstance(CommandServiceforPlayer.class);
    }
}