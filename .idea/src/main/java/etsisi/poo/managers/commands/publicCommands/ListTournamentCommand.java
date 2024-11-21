package etsisi.poo.managers.commands.publicCommands;
import etsisi.poo.LoggedSingleton;
import etsisi.poo.managers.TournamentManager;
import etsisi.poo.managers.UserManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.modelo.User;
import etsisi.poo.modelo.Player;

public class ListTournamentCommand implements Command{
    private final TournamentManager tournamentManager;
    private final LoggedSingleton loggedSingleton;

    public ListTournamentCommand(TournamentManager tournamentManager, LoggedSingleton loggedSingleton) {
        this.tournamentManager = tournamentManager;
        this.loggedSingleton= loggedSingleton;
    }
    public String execute(String parameter) {
        String resul="";
        resul=tournamentManager.tournamentList((Player)loggedSingleton.getUsuarioLogueado());
        return resul;
    }
}
