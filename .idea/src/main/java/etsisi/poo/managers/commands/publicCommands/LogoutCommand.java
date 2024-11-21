package etsisi.poo.managers.commands.publicCommands;

import etsisi.poo.LoggedSingleton;
import etsisi.poo.managers.UserManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.User;

public class LogoutCommand  implements Command {
    private final LoggedSingleton loggedSingleton;
    public LogoutCommand( LoggedSingleton loggedSingleton) {

        this.loggedSingleton = loggedSingleton;
    }
    public String execute(String parameter) {
        String result = "";

        result=loggedSingleton.out();
        // Verifica si el usuario fue autenticado
        return result;
    }

}
