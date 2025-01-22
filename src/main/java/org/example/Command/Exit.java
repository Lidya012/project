package org.example.Command;

import org.example.Command.AbstractCommand;
import org.example.Util.SAVED.SaveState;

public class Exit extends AbstractCommand {
    public Exit() {
        super("Выход");
    }
    @Override
    public boolean execute() {
        SaveState.saveState();
        System.exit(0);
        return false;
    }
}
