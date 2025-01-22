package org.example.Command.Service;

import org.example.Command.AbstractCommand;
import org.example.Command.Service.Commands.AdminCommands.AddUsers;
import org.example.Command.Service.Commands.AdminCommands.CreateTournament;
import org.example.Command.Service.Commands.AdminCommands.GetAllTournament;
import org.example.Command.Service.Commands.AdminCommands.RemoveUser;
import org.example.Command.Exit;

public class CommandServisforAdmin extends CommandService<AbstractCommand> {
    protected CommandServisforAdmin() {
        super();
    }

    @Override
    protected void initializeCommands() {
        commands.add(new AddUsers());
        commands.add(new CreateTournament()) ;
        commands.add(new GetAllTournament());
        commands.add(new RemoveUser());
        commands.add(new Exit());
    }

    public static CommandServisforAdmin getInstance() {
        return (CommandServisforAdmin) CommandService.getInstance(CommandServisforAdmin.class);
    }
}
