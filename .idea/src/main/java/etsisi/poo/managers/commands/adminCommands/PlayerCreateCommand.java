package etsisi.poo.managers.commands.adminCommands;

import etsisi.poo.managers.UserManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.Admin;
import etsisi.poo.modelo.Player;




public class PlayerCreateCommand implements Command {
    private final Admin admin;
    private final UserManager playerManager;
    private final UserManager userManager;

    public PlayerCreateCommand(Admin admin, UserManager playerManager, UserManager userManager) {
        this.admin = admin;
        this.playerManager = playerManager;
        this.userManager = userManager;
    }

    @Override
    public String execute(String parametro) {
        String resul = "";
        if (!parametro.isEmpty()) {
            String[] multipleParameters = Utilities.splitArgs(parametro);
            if (multipleParameters.length == 5) {
                String name = multipleParameters[0];
                String surname = multipleParameters[1];
                String dni = multipleParameters[2];
                String username = multipleParameters[3];
                String password = multipleParameters[4];

                if (!name.equals("") && !name.contains(" ") && !surname.equals("") && !surname.contains(" ")
                        && !playerManager.existsPlayerWithDni(dni) && !playerManager.existsUserWithUsername(username)) {
                    if (password.length() >= Utilities.PASSWORD_LENGTH) {
                        if (dni.length() == Utilities.DNI_LENGTH) {
                            Player jugador = new Player(name, surname, playerManager, dni, username, password);
                            admin.getListaAltasJugadores().add(jugador);
                            userManager.add(jugador);
                            resul = jugador.toString();
                        } else
                            resul = Utilities.DNI_FORMAT_ERROR;
                    } else
                        resul = Utilities.PASSWORD_FORMAT_ERROR;
                } else {
                    resul = Utilities.CANT_CREATE_PLAYER;
                }
            } else {
                resul = Utilities.WRONG_ARGS;
            }
        } else {
            resul = Utilities.WRONG_ARGS;
        }
        return resul;
    }
}





