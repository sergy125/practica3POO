package etsisi.poo.managers.commands.adminCommands;

import etsisi.poo.managers.TeamManager;
import etsisi.poo.managers.TournamentManager;
import etsisi.poo.managers.UserManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.Admin;
import etsisi.poo.modelo.Team;

public class PlayerDeleteCommand implements Command{
    private final Admin admin;
    private final TournamentManager tournamentManager;
    private final UserManager playerManager;
    private final TeamManager teamManager;
    public PlayerDeleteCommand(Admin admin, TournamentManager tournamentManager, UserManager playerManager,TeamManager teamManager){
        this.admin= admin;
        this.tournamentManager= tournamentManager;
        this.playerManager= playerManager;
        this.teamManager= teamManager;
    }

    public  String execute(  String parametro) {
        String resul = "";
        if (!parametro.isEmpty()) {
            String[] multipleParameters= Utilities.splitArgs(parametro);
            if(multipleParameters.length==1) { //solo debe haber 1 argumento: el username
                String username = multipleParameters[0];
                if (!tournamentManager.existsActiveTournamentWithPlayerAlone(username) && !tournamentManager.existsActiveTournamentWithPlayerInTeam(username)) {
                    TeamManager newTeamManager = teamManager;
                    while (newTeamManager.existsTeamWithPlayer(username)) {
                        Team team = newTeamManager.getTeamWithPlayer(username);
                        if (team.justOnePlayer()) {
                            newTeamManager.removeTeamWithName(team.getName());
                            teamManager.removeTeamWithName(team.getName());
                            admin.getListaAltasEquipos().removeTeamWithName(team.getName());
                        }else{
                            team.removePlayer(playerManager.getPlayerWithUsername(username));
                        }
                        newTeamManager.removeTeamWithName(team.getName());

                    }
                    playerManager.removeUser(username);
                    admin.getListaAltasJugadores().removeUser(username);
                    resul = Utilities.ELIMINATED_PLAYER;
                } else resul = Utilities.ACTIVE_TOURNAMENT_ERROR;
            }else{resul=Utilities.WRONG_ARGS;}

            //MIRAR LOS ERRORES PQ ESTAN PIESTOS AHI PERO HAY QUE CAMBIARLOS
        } else {
            resul = Utilities.WRONG_ARGS;
        }
        return resul;
    }

}
