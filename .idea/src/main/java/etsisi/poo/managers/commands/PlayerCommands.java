package etsisi.poo.managers.commands;

import etsisi.poo.managers.TournamentManager;
import etsisi.poo.modelo.Player;
import etsisi.poo.modelo.Tournament;

public class PlayerCommands {

    public static String tournamentAdd2(Player actualLogIn, String parametro, TournamentManager tournamentManager) {
        String resul="";
        String parts[] = parametro.split(";");
        String torunamentName = parts[0];
        String teamName="";
        if(parts[1]!=null) teamName=parts[1];
        if(!tournamentManager.existTournamentWithName(torunamentName)){
            Tournament tournament=tournamentManager.getTournamentWithName(torunamentName);
            if(!tournament.isRunning()){
                if(!teamName.equals("")&&tournament.getTeams().existsTeamWithName(teamName)){
                    if(!tournament.getTeams().getTeam(teamName).existsPlayer(actualLogIn.getUsername())){
                        tournament.getTeams().getTeam(teamName).addPlayer(actualLogIn);
                    } //ESTA OPCION ES SI NOS DICEN CON EQUIPO PUES COMPROBAMOS QUE NO ESTE EN EL EQUIPO
                } else {
                    if(!tournament.getPlayers().existsUserWithUsername(actualLogIn.getUsername())){
                        tournament.getPlayers().add(actualLogIn);
                    }//ESTA OPCION ES SI NOS DICEN SIN EQUIPO PUES QUE NO ESTE EN EL TORNEO
                }
            }else resul = Utilities.WRONG_ARGS;//TOURNAMENT RUNNING Y NO SE PUEDE METER

        }
        return resul;
    }
    public static String tournamentRemove(Player actualLogIn,String parametro, TournamentManager tournamentManager) {
        String resul = "";
        String parts[] = parametro.split(";");
        String torunamentName = parts[0];
        String teamName = "";
        if (parts[1] != null) teamName = parts[1];
        if (!tournamentManager.existTournamentWithName(torunamentName)) {
            Tournament tournament = tournamentManager.getTournamentWithName(torunamentName);
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
    public static String showStatistics(Player actualLogIn,String parametro) {
        String resul = "";
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
