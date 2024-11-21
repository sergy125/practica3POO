package etsisi.poo.managers.commands.adminCommands;

import etsisi.poo.managers.TeamManager;
import etsisi.poo.managers.UserManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.Player;
import etsisi.poo.modelo.Team;

public class TeamRemoveCommand implements Command { // elimina jugador del equipo
    private final UserManager playerManager;
    private final TeamManager teamManager;
    public TeamRemoveCommand(UserManager playerManager, TeamManager teamManager){
        this.playerManager= playerManager;
        this.teamManager= teamManager;
    }

    public String execute( String parametro) {
        String resul = "";

        if (!parametro.isEmpty()) {
            String[] multipleParameters = Utilities.splitArgs(parametro);
            String teamName = multipleParameters[0];
            String username = multipleParameters[1];

            if (teamManager.existsTeamWithName(teamName)) { //El equipo existe
                Team team = teamManager.getTeam(teamName);

                if (teamManager.existsTeamWithPlayer(username)) { //El jugador pertenece a un equipo

                    if (team.existsPlayer(username)) { //El jugador está en 'team'
                        if(team.getNumPlayers()>1) {
                            Player player = playerManager.getPlayerWithUsername(username);
                            team.removePlayer(player);
                            resul = "Player removed from team. \n" + team.toString();
                        } else resul = Utilities.ONE_PLAYER_TEAM;
                    } else resul = Utilities.PLAYER_NOT_IN_TEAM;  //El jugador no está en 'team'

                } else resul = Utilities.CANT_REMOVE_PLAYER_FROM_TEAM;  //El jugador no pertenece a ningun equipo/no existe

            } else resul = Utilities.TEAM_DOESNT_EXIST;  //No existe 'team'
        } else resul = Utilities.WRONG_ARGS;
        return resul;
    }
}
