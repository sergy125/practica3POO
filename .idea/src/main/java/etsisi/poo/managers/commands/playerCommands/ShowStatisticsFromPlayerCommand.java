package etsisi.poo.managers.commands.playerCommands;

import etsisi.poo.LoggedSingleton;
import etsisi.poo.managers.TournamentManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.Player;
import etsisi.poo.modelo.Tournament;

public class ShowStatisticsFromPlayerCommand implements Command {
    private final LoggedSingleton loggedSingleton;
    private final TournamentManager tournamentManager;
    public ShowStatisticsFromPlayerCommand(LoggedSingleton loggedSingleton, TournamentManager tournamentManager) {
        this.loggedSingleton = loggedSingleton;
        this.tournamentManager = tournamentManager;
    }
    public String execute(String parametro) {
        String resul = "";
        Player actualLogIn = (Player) loggedSingleton.getUsuarioLogueado();
        if (parametro.equals("csv")) {
            resul = actualLogIn.getCategoryMap().toStringCSV();
        } else if (parametro.equals("json")) {
            resul = actualLogIn.getCategoryMap().toStringJSON();
        } else {
            resul =Utilities.WRONG_ARGS;
        }

        return resul;
    }

}
