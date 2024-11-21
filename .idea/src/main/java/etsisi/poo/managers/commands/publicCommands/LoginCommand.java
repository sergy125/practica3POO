package etsisi.poo.managers.commands.publicCommands;

import etsisi.poo.LoggedSingleton;
import etsisi.poo.managers.UserManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.User;

public class LoginCommand implements Command {
    private final UserManager userManager;
    private final LoggedSingleton loggedSingleton;

    public LoginCommand(UserManager userManager, LoggedSingleton loggedSingleton) {
        this.userManager = userManager;
        this.loggedSingleton = loggedSingleton;
    }

    @Override
    public String execute(String parameter) {
        String result = "";
        if (parameter == null || parameter.isEmpty()) {
            return Utilities.WRONG_ARGS;
        }

        loggedSingleton.log(userManager, parameter);

        // Verifica si el usuario fue autenticado
        User loggedUser = loggedSingleton.getUsuarioLogueado();
        if (loggedUser != null) {
            result= "Inicio de sesión exitoso: " + loggedUser.toString();
        } else {
            result= Utilities.USERNAME_PASSWORD_ERROR;
        }
        return result;
    }

}
