package etsisi.poo.managers.commands.adminCommands;

import etsisi.poo.managers.TeamManager;
import etsisi.poo.managers.UserManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.Admin;
import etsisi.poo.modelo.Player;
import etsisi.poo.modelo.Team;

public class TeamCreateCommand implements Command { // elima equipo del sistema
    private final Admin admin;
    private final UserManager allPlayers;
    private final TeamManager teamManager;
    public TeamCreateCommand(Admin admin, UserManager allPlayers, TeamManager teamManager){
        this.admin= admin;
        this.allPlayers= allPlayers;
        this.teamManager= teamManager;

    }

    public String execute(String parametro) {
        String resul = "";
        if (!parametro.isEmpty()) {
            // Eliminar los corchetes del nombre del jugador
            //String[] multipleParameters = parametro.split(";");
            String[] multipleParameters = Utilities.splitArgs(parametro);
            if (multipleParameters.length >= 2) {
                String name = multipleParameters[0];
                String username = multipleParameters[1];    //Siempre se va a añadir mínimo 1 jugador al equipo

                if (!teamManager.existsTeamWithName(name) && !name.equals("")) {
                    if (username != null && allPlayers.existsUserWithUsername(username)) {
                        Player player1 = allPlayers.getPlayerWithUsername(username);
                        Team team = new Team(name, teamManager, player1);
                        for (int i = 2; i < multipleParameters.length; i++) {
                            username = multipleParameters[i];
                            Player player = null;
                            if (username != null && allPlayers.existsUserWithUsername(username)) {
                                player = allPlayers.getPlayerWithUsername(username);
                                team.addPlayer(player);
                            } else resul = "No se ha encontrado a un jugador";
                        }
                        admin.getListaAltasEquipos().addTeam(team);
                        resul = "Team created: " + team.toString();
                    } else resul = "No se encuentra al jugador";
                }else resul = "No se puede crear un equipo con ese nombre";
            } else {
                resul = Utilities.CANT_CREATE_TEAM;
            }
        }else resul="Tienes que introducir al menos un jugador";
        return resul;

    }

}
