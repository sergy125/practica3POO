package etsisi.poo.managers.commands.adminCommands;

import etsisi.poo.managers.TeamManager;
import etsisi.poo.managers.TournamentManager;
import etsisi.poo.managers.commands.Command;
import etsisi.poo.managers.commands.Utilities;
import etsisi.poo.modelo.Admin;

public class TeamDeleteCommand implements Command {
    private final Admin admin;
    private final TournamentManager tournamentManager;
    private final TeamManager teamManager;
    public TeamDeleteCommand(Admin admin, TournamentManager tournamentManager, TeamManager teamManager){
        this.admin= admin;
        this.tournamentManager= tournamentManager;
        this.teamManager= teamManager;
    }
    public  String execute(  String parametro) {
        String resul = "";
        if (!parametro.isEmpty()) {
            if (!tournamentManager.existsActiveTournamentWithTeam(parametro) && admin.getListaAltasEquipos().existsTeamWithName(parametro)) { //Si no existe un torneo en curso con este equipo
                admin.getListaAltasEquipos().removeTeamWithName(parametro); //Elimina el equipo de la lista asociada al admin
                teamManager.removeTeamWithName(parametro);  //Elimina el equipo de la lista de equipos general
                resul = "Team "+parametro+" deleted";
                System.out.println(admin.getListaAltasEquipos().toString());
            } else resul = Utilities.CANT_DELETE_TEAM;
        }else resul= Utilities.WRONG_ARGS;
        return resul;
    }
}
