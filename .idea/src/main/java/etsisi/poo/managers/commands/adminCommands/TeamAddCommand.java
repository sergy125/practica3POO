package etsisi.poo.managers.commands.adminCommands;

import etsisi.poo.managers.TeamManager;
import etsisi.poo.managers.UserManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.Player;
import etsisi.poo.modelo.Team;

public class TeamAddCommand implements Command{ // añade jugador en el grupo
    private final UserManager playerManager;
    private final TeamManager teamManager;

    public TeamAddCommand(UserManager playerManager, TeamManager teamManager){
        this.playerManager= playerManager;
        this.teamManager= teamManager;

    }
    public String execute( String parametro) {
        String resul = "";
        if (!parametro.isEmpty()) {
            String[] multipleParameters = Utilities.splitArgs(parametro);
            String teamName = multipleParameters[0];
            String username = multipleParameters[1];

            if (teamManager.existsTeamWithName(teamName)) {
                Team team = teamManager.getTeam(teamName);
                if(!team.existsPlayer(username) && !teamManager.existsTeamWithPlayer(username)) {
                    Player player = playerManager.getPlayerWithUsername(username);
                    team.addPlayer(player);
                    resul = "Player added to team. \n"+team.toString();

                } else resul = Utilities.CANT_ADD_PLAYER_TO_TEAM;  //No se ha podido añadir el jugador pq ya tiene equipo/no existe el jugador

            } else resul = Utilities.TEAM_DOESNT_EXIST;  //No existe el equipo
        } else resul = Utilities.WRONG_ARGS;

        return resul;
    }

}
