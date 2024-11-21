package etsisi.poo.managers.commands.playerCommands;
import etsisi.poo.LoggedSingleton;
import etsisi.poo.managers.TournamentManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.Player;
import etsisi.poo.modelo.Tournament;

public class RemovePlayerFromTournamentCommand implements Command {
    private final LoggedSingleton loggedSingleton;
    private final TournamentManager tournamentManager;
    public RemovePlayerFromTournamentCommand(LoggedSingleton loggedSingleton, TournamentManager tournamentManager) {
        this.loggedSingleton = loggedSingleton;
        this.tournamentManager = tournamentManager;
    }
    public String execute(String parametro) {
        String resul = "";
        String parts[] = parametro.split(";");
        String torunamentName = parts[0];
        String teamName = "";
        if (parts[1] != null) teamName = parts[1];
        if (!tournamentManager.existTournamentWithName(torunamentName)) {
            Tournament tournament = tournamentManager.getTournamentWithName(torunamentName);
            Player actualLogIn = (Player) loggedSingleton.getUsuarioLogueado();
            if (!teamName.equals("") && tournament.getTeams().existsTeamWithName(teamName)) {
                if (tournament.getTeams().getTeam(teamName).existsPlayer(actualLogIn.getUsername())) {
                    tournament.getTeams().getTeam(teamName).removePlayer(actualLogIn);
                } //ESTA OPCION ES SI NOS DICEN CON EQUIPO PUES COMPROBAMOS QUE NO ESTE EN EL EQUIPO
            } else {
                if (tournament.getPlayers().existsUserWithUsername(actualLogIn.getUsername())) {
                    tournament.getPlayers().removeUser(actualLogIn.getUsername());
                }
            }
        } else {
            resul =Utilities.WRONG_ARGS;
        }
        return resul;
    }

}
