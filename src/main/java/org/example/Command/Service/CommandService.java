package org.example.Command.Service;

import org.example.Util.UtilInput;
import org.example.Command.AbstractCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandService <T extends AbstractCommand> implements StartWorking {
    private static CommandService<?> instance;
    protected List<T> commands = new ArrayList<>();

    protected CommandService() {
        initializeCommands();
    }

    protected void initializeCommands() {

    }

    public static <T extends CommandService<?>> T getInstance(Class<T> clazz) {
        if (instance == null) {
            try {
                instance = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) instance;
    }

    @Override
    public boolean printCommand() {
        boolean isWorking = true;
        while (isWorking) {
            System.out.println("Выберите действие");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(String.format("%s - %s", i, commands.get(i).toString()));
            }
            int result = UtilInput.getRequiredIntFromUser(0, commands.size() - 1);
            isWorking = commands.get(result).execute();
        }
        return isWorking;
    }
}
