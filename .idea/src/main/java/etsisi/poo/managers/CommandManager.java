package etsisi.poo.managers;

import etsisi.poo.managers.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String, Command> commands = new HashMap<>();

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public String executeCommand(String commandName, String parametro) {
        String resul="";
        Command command = commands.get(commandName);
        if (command != null) {
            resul =command.execute(parametro);
        } else {
            resul = "Comando no encontrado.";
        }
        return resul;
    }
}
