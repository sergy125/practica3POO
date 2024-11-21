package etsisi.poo.managers.commands.publicCommands;

import etsisi.poo.managers.UserManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.User;

public class RegisterCommand implements Command {
    private UserManager userManager;
    public RegisterCommand(UserManager userManager) {
        this.userManager = userManager;
    }
    public String execute(String parametro) {

        String resul = "";
        if (parametro.contains(";")) {
            String[] credenciales = Utilities.splitArgs(parametro);
            if (credenciales.length == 2) {
                String correo = credenciales[0];
                String password = credenciales[1];
                String[] user = correo.split("@");
                if (!userManager.existsUserWithUsername(user[0])) {
                    if (userManager == null) userManager = new UserManager();
                    User newUser = new User(user[0], password, userManager, true);
                    resul="Usuario creado con éxito: " + newUser.toString();
                } else resul=Utilities.EXISTS_USER;
            } else resul=Utilities.WRONG_ARGS;
        } else resul=Utilities.WRONG_ARGS;

        return resul;
    }


}
